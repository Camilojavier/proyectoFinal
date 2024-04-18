package com.camilo.arce.proyecto.web.rest;

import com.camilo.arce.proyecto.domain.entities.LoginRequest;
import com.camilo.arce.proyecto.domain.entities.OpenIDRequest;
import com.camilo.arce.proyecto.domain.entities.UsernamePasswordRequest;
import com.camilo.arce.proyecto.dto.*;
import com.camilo.arce.proyecto.services.AuthService;
import com.camilo.arce.proyecto.services.DiscoveryService;
import com.camilo.arce.proyecto.services.ProviderDetailsService;
import com.camilo.arce.proyecto.services.ProvidersService;
import com.camilo.arce.proyecto.tool.CookieUtils;
import com.camilo.arce.proyecto.tool.DiscoveryComparator;
import com.camilo.arce.proyecto.tool.ProviderDetailsComparator;
import com.camilo.arce.proyecto.web.api.AuthApi;
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

    @GetMapping(OPENID_ROUTE)
    public ResponseEntity<Map<String, String>> getOpenIDProviders() {
        List<ProviderDetailsDto> providersDtoList = providerDetailsService.getAllProviderDetails();
        providersDtoList.sort(new ProviderDetailsComparator());
        List<DiscoveryDto> discoveryDtoList = discoveryService.getAllDiscoveries();
        discoveryDtoList.sort(new DiscoveryComparator());
        Map<String, String> providersRequests = new HashMap<>();
        for (int i = 0; i < Math.min(discoveryDtoList.size(), providersDtoList.size()); i++) {
            providersRequests.put(discoveryDtoList.get(i).getProviders().getName(), getUrl(providersDtoList.get(i), discoveryDtoList.get(i)));
        }
        return ResponseEntity.ok(providersRequests);
    }

    private String getUrl(ProviderDetailsDto provider, DiscoveryDto discovery) {
        Long id = provider.getProviders().getProviderId();
        Optional<ProvidersDto> providersDto = providersService.getProviderById(id);
        ProvidersDto providers;
        if (providersDto.isPresent()) {
            providers = providersDto.get();
        } else {
            return null;
        }
        String url = discovery.getAuthEndpoint() + "?" +
                "redirect_uri=" + REDIRECT_URI + "&" +
                "client_id=" + providers.getClientId() + "&" +
                "state=" + id + "state" + "&" +
                "nonce=" + "nonce";

        if (provider.getExtraScopes() != null) {
            url += "&scope=openid email" + provider.getExtraScopes();
        } else {
            url += "&scope=openid email";
        }
        if (provider.getResponseType() != null) {
            url += "&response_type=" + provider.getResponseType();
        } else {
            url += "&response_type=code";
        }
        if (providers.getResponseMode() != null) {
            url += "&response_mode=" + providers.getResponseMode();
        }
        if (provider.getDisplay() != null) {
            url += "&display=" + provider.getDisplay();
        }
        if (provider.getPrompt() != null) {
            url += "&prompt=" + provider.getPrompt();
        }
        return url;
    }
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

    @PostMapping(LOGIN_ROUTE)
    public void login(@RequestBody UsernamePasswordRequest usernamePasswordRequest, HttpServletResponse response) throws Exception {
        performAuthentication(usernamePasswordRequest, response);
    }

    private void performAuthentication( LoginRequest loginRequest, HttpServletResponse response) throws Exception {
        AuthResponseDto responseDTO = authService.login(loginRequest);
        IdTokenDto idTokenDTO = responseDTO.getToken();
        if (idTokenDTO.isValidToken()) {
            Cookie cookie = new Cookie(AUTH_TOKEN, CookieUtils.encryptIdTokenDTO(idTokenDTO));
            cookie.setPath("/");
            cookie.setMaxAge(600);
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
        }
    }

}
