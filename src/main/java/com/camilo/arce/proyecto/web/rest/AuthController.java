package com.camilo.arce.proyecto.web.rest;

import com.camilo.arce.proyecto.domain.entities.OpenIDRequest;
import com.camilo.arce.proyecto.dto.*;
import com.camilo.arce.proyecto.services.AuthService;
import com.camilo.arce.proyecto.services.DiscoveryService;
import com.camilo.arce.proyecto.services.ProviderDetailsService;
import com.camilo.arce.proyecto.services.ProvidersService;
import com.camilo.arce.proyecto.tool.CookieUtils;
import com.camilo.arce.proyecto.tool.DiscoveryComparator;
import com.camilo.arce.proyecto.tool.ProviderDetailsComparator;
import com.camilo.arce.proyecto.web.api.AuthApi;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(AuthApi.AUTH_ROUTE)
public class AuthController implements AuthApi {
    public static final String REDIRECT_URI = "http://localhost:8080/auth/oidc";
    private final AuthService authService;
    private final ProvidersService providersService;
    private final ProviderDetailsService providerDetailsService;
    private final DiscoveryService discoveryService;
    @PostMapping(OPENID_ROUTE)
    public ResponseEntity<AuthResponseDTO> openIDLogin(@RequestParam(CODE) String code, @RequestParam(STATE) String state, HttpServletResponse response) throws Exception {
        ProvidersDto provider = findProvider(state);
        Optional<DiscoveryDto> discoveryOpt;
        DiscoveryDto discovery;
        if (provider!= null) {
            discoveryOpt = discoveryService.getDiscoveryByProviderId(provider.getProviderId());
            if (discoveryOpt.isPresent())
                discovery = discoveryOpt.get();
            else{
                return null;
            }
        }
        else {
            return null;
        }

        final OpenIDRequest openIDRequest = new OpenIDRequest(discovery.getIssuer(),discovery.getTokenEndpoint(), code, state,
                provider.getClientId(), provider.getClientSecret(), REDIRECT_URI, discovery.getJwksUri(),"nonce" );
        AuthResponseDTO responseDTO = authService.login(openIDRequest);
        IdTokenDTO idTokenDTO = responseDTO.getToken();
        if (idTokenDTO.isValidToken()) {
            Cookie cookie = new Cookie("auth_token", CookieUtils.encryptIdTokenDTO(idTokenDTO));
            cookie.setPath("/");
            cookie.setMaxAge(600);
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
        }
        return ResponseEntity.ok(responseDTO);
    }

    private ProvidersDto findProvider(String state) {
        Optional<ProvidersDto> providersDto = providersService.getProviderById((long)Character.getNumericValue(state.charAt(0)));
        return providersDto.orElse(null);
    }

    @GetMapping(OPENID_ROUTE)
    public ResponseEntity<List<String>> getOpenIDProviders(){
        List<ProviderDetailsDto> providersDtoList = providerDetailsService.getAllProviderDetails();
        providersDtoList.sort(new ProviderDetailsComparator());
        List<DiscoveryDto> discoveryDtoList = discoveryService.getAllDiscoveries();
        discoveryDtoList.sort(new DiscoveryComparator());
        List<String> providersRequests = new ArrayList<>();
        for (int i = 0; i< discoveryDtoList.size();i++){
            providersRequests.add( getUrl(providersDtoList.get(i), discoveryDtoList.get(i)) );
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
                "scope=openid email" + provider.getExtraScopes() + "&" +
                "response_type=" + provider.getResponseType() + "&" +
                "client_id=" + providers.getClientId() + "&" +
                "redirect_uri=" + REDIRECT_URI + "&" +
                "state=" + id +  "state" + "&" +
                "nonce=" + "nonce";
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


}
