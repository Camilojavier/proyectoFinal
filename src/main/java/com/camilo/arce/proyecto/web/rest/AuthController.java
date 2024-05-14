package com.camilo.arce.proyecto.web.rest;

import com.camilo.arce.proyecto.dto.LoginRequest;
import com.camilo.arce.proyecto.domain.entities.OpenIDRequest;
import com.camilo.arce.proyecto.dto.UsernamePasswordRequest;
import com.camilo.arce.proyecto.dto.*;
import com.camilo.arce.proyecto.services.*;
import com.camilo.arce.proyecto.tools.AuthTokenCrypt;
import com.camilo.arce.proyecto.tools.DiscoveryComparator;
import com.camilo.arce.proyecto.tools.ProviderDetailsComparator;
import com.camilo.arce.proyecto.tools.ProviderRequestBuilder;
import com.camilo.arce.proyecto.web.api.AuthApi;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(AuthApi.AUTH_ROUTE)
public class AuthController implements AuthApi {
    private final AuthService authService;
    private final ProvidersService providersService;
    private final ProviderDetailsService providerDetailsService;
    private final DiscoveryService discoveryService;
    private final UsersService usersService;
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);


    @Operation(summary = "Login With OIDC")
    @PostMapping(OPENID_ROUTE)
    public ResponseEntity<AuthResponseDto> openIDLogin(@RequestParam(CODE) String code, @RequestParam(STATE) String state, HttpServletResponse response) {
        try {
            if (state == null || code == null) {
                throw new IllegalArgumentException("code and state are required during the request");
            }
            LOGGER.info("OpenID Login With OIDC code: {} and state: {}", code, state);
            ProvidersDto provider = findProvider(state);
            LOGGER.info("Found provider: {}", provider);
            Optional<DiscoveryDto> discoveryOpt;
            DiscoveryDto discovery;
            if (provider != null) {
                discoveryOpt = discoveryService.getDiscoveryByProviderId(provider.getProviderId());
                if (discoveryOpt.isPresent()) {
                    discovery = discoveryOpt.get();
                    LOGGER.info("Found discovery: {}", discovery);
                } else {
                    LOGGER.error("No discovery found for provider: {}", provider.getProviderId());
                    return new ResponseEntity<>(AuthResponseDto.error("Discovery Error", 500), HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                LOGGER.error("No provider found for state: {}", state);
                return new ResponseEntity<>(AuthResponseDto.error("Provider Error", 500), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            //TODO: Improve NONCE and State
            final OpenIDRequest openIDRequest =
                    new OpenIDRequest(provider.getName(),
                            discovery.getIssuer(),
                            discovery.getTokenEndpoint(),
                            code, state,
                            provider.getClientId(),
                            provider.getClientSecret(),
                            REDIRECT_URI,
                            discovery.getJwksUri(),
                            "nonce");
            LOGGER.info("Creating Request: {}", openIDRequest);
            return performAuthentication(openIDRequest, response, true);
        }catch (IllegalArgumentException e){
            LOGGER.error("Invalid request parameters: {}", e.getMessage());
            return new ResponseEntity<>(AuthResponseDto.error("Invalid request parameters", HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            LOGGER.error("Unexpected error: {}", e.getMessage());
            return new ResponseEntity<>(AuthResponseDto.error("Unexpected error", 500), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ProvidersDto findProvider(String state) {
        int primeraLetra = 0;
        for (int i = 0; i < state.length(); i++) {
            if (!Character.isDigit(state.charAt(i))) {
                primeraLetra = i;
                break;
            }
        }
        String receivedId = state.substring(0, primeraLetra);
        Long providerId = Long.parseLong(receivedId);
        Optional<ProvidersDto> providersDto = providersService.getProviderById(providerId);
        return providersDto.orElse(null);
    }

    @Operation(summary = "Get OIDC Providers")
    @GetMapping(OPENID_ROUTE)
    public ResponseEntity<Map<String, String>> getOpenIDProviders() {
        LOGGER.info("Fetching Configured Providers");
        List<ProviderDetailsDto> detailsDtoList = providerDetailsService.getAllProviderDetails();
        LOGGER.info("Found {} Providers", detailsDtoList.size());
        detailsDtoList.sort(new ProviderDetailsComparator());
        List<DiscoveryDto> discoveryDtoList = discoveryService.getAllDiscoveries();
        discoveryDtoList.sort(new DiscoveryComparator());
        Map<String, String> providersRequests = new HashMap<>();
        for (int i = 0; i < discoveryDtoList.size(); i++) {
            final String authEndpoint = discoveryDtoList.get(i).getAuthEndpoint();
            providersRequests.put(discoveryDtoList.get(i).getProviders().getName(),
                    ProviderRequestBuilder.getUrl(detailsDtoList.get(i), authEndpoint));
            LOGGER.info("Created URL: {}", authEndpoint);
        }
        return ResponseEntity.ok(providersRequests);
    }

    @Operation(summary = "Log Out")
    @GetMapping(LOGOUT_ROUTE)
    public void logout(HttpServletResponse response) {
        LOGGER.info("Logging Out");
        removeAuthToken(response);
    }


    private void removeAuthToken(HttpServletResponse response) {
        Cookie cookie = new Cookie(AUTH_TOKEN, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }


    @Operation(summary = "Username Password Login")
    @PostMapping(LOGIN_ROUTE)
    public ResponseEntity<AuthResponseDto> login(@RequestBody UsernamePasswordRequest usernamePasswordRequest, HttpServletResponse response) throws Exception {
        return performAuthentication(usernamePasswordRequest, response, false);
    }


    private ResponseEntity<AuthResponseDto> performAuthentication( LoginRequest loginRequest, HttpServletResponse response, boolean redirect) throws Exception {
        AuthResponseDto responseDTO = authService.login(loginRequest);
        LOGGER.info("AuthResponse: {}", responseDTO);
        IdTokenDto idTokenDTO = responseDTO.getToken();
        if (idTokenDTO == null) {
            LOGGER.error("IdToken is null");
            if (redirect)
                response.sendRedirect(REFERER);
            return new ResponseEntity<>(responseDTO,HttpStatus.UNAUTHORIZED);
        }
        if (idTokenDTO.isValidToken()) {
            LOGGER.info("IdToken is valid: {}", idTokenDTO);
            Cookie cookie = new Cookie(AUTH_TOKEN, AuthTokenCrypt.encryptIdTokenDTO(idTokenDTO));
            cookie.setPath("/");
            cookie.setMaxAge(600);
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
            if (redirect)
                response.sendRedirect(REFERER);

            return new ResponseEntity<>(responseDTO,HttpStatus.OK);
        }
        LOGGER.error("IdToken is invalid: {}", idTokenDTO);
        if (redirect)
            response.sendRedirect(REFERER);
        return new ResponseEntity<>(responseDTO, HttpStatus.UNAUTHORIZED);
    }


    @Operation(summary = "User Registration")
    @PostMapping(REGISTER_ROUTE)
    public ResponseEntity<UsersDto> register(@RequestBody UsersDto usersDto) {
        UsersDto createdUserDTO = usersService.createUser(usersDto);
        LOGGER.info("Created User {}", createdUserDTO);
        return new ResponseEntity<>(createdUserDTO, HttpStatus.CREATED);
        }

}
