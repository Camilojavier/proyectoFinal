package com.camilo.arce.proyecto.web.rest;

import com.camilo.arce.proyecto.domain.entities.LoginRequest;
import com.camilo.arce.proyecto.domain.entities.OpenIDRequest;
import com.camilo.arce.proyecto.domain.entities.UsernamePasswordRequest;
import com.camilo.arce.proyecto.dto.*;
import com.camilo.arce.proyecto.services.AuthService;
import com.camilo.arce.proyecto.services.DiscoveryService;
import com.camilo.arce.proyecto.services.ProviderDetailsService;
import com.camilo.arce.proyecto.services.ProvidersService;
import com.camilo.arce.proyecto.tools.AuthTokenCrypt;
import com.camilo.arce.proyecto.tools.DiscoveryComparator;
import com.camilo.arce.proyecto.tools.ProviderDetailsComparator;
import com.camilo.arce.proyecto.tools.ProviderRequestBuilder;
import com.camilo.arce.proyecto.web.api.AuthApi;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
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

    @Operation(summary = "Login With OIDC")
    @PostMapping(OPENID_ROUTE)
    public void openIDLogin(@RequestParam(CODE) String code, @RequestParam(STATE) String state, HttpServletResponse response) throws Exception {
        ProvidersDto provider = findProvider(state);
        Optional<DiscoveryDto> discoveryOpt;
        DiscoveryDto discovery;
        if (provider != null) {
            discoveryOpt = discoveryService.getDiscoveryByProviderId(provider.getProviderId());
            if (discoveryOpt.isPresent())
                discovery = discoveryOpt.get();
            else {
                return;
            }
        } else {
            return;
        }

        final OpenIDRequest openIDRequest = new OpenIDRequest(provider.getName(), discovery.getIssuer(), discovery.getTokenEndpoint(), code, state,
                provider.getClientId(), provider.getClientSecret(), REDIRECT_URI, discovery.getJwksUri(), "nonce");
        performAuthentication(openIDRequest, response);
        response.sendRedirect(REFERER);
    }

    private ProvidersDto findProvider(String state) {
        Optional<ProvidersDto> providersDto = providersService.getProviderById((long) Character.getNumericValue(state.charAt(0)));
        return providersDto.orElse(null);
    }

    @Operation(summary = "Get OIDC Providers")
    @GetMapping(OPENID_ROUTE)
    public ResponseEntity<Map<String, String>> getOpenIDProviders() {
        List<ProviderDetailsDto> detailsDtoList = providerDetailsService.getAllProviderDetails();
        detailsDtoList.sort(new ProviderDetailsComparator());
        List<DiscoveryDto> discoveryDtoList = discoveryService.getAllDiscoveries();
        discoveryDtoList.sort(new DiscoveryComparator());
        Map<String, String> providersRequests = new HashMap<>();
        for (int i = 0; i < discoveryDtoList.size(); i++) {
            final String authEndpoint = discoveryDtoList.get(i).getAuthEndpoint();
            providersRequests.put(discoveryDtoList.get(i).getProviders().getName(),
                    ProviderRequestBuilder.getUrl(detailsDtoList.get(i), authEndpoint));
        }
        return ResponseEntity.ok(providersRequests);
    }

    @Operation(summary = "Log Out")
    @GetMapping(LOGOUT_ROUTE)
    public void logout(HttpServletResponse response) {
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
    public void login(@RequestBody UsernamePasswordRequest usernamePasswordRequest, HttpServletResponse response) throws Exception {
        performAuthentication(usernamePasswordRequest, response);
    }

    private void performAuthentication( LoginRequest loginRequest, HttpServletResponse response) throws Exception {
        AuthResponseDto responseDTO = authService.login(loginRequest);
        IdTokenDto idTokenDTO = responseDTO.getToken();
        if (idTokenDTO.isValidToken()) {
            Cookie cookie = new Cookie(AUTH_TOKEN, AuthTokenCrypt.encryptIdTokenDTO(idTokenDTO));
            cookie.setPath("/");
            cookie.setMaxAge(600);
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
        }
    }

}
