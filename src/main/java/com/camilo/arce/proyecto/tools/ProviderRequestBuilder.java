package com.camilo.arce.proyecto.tools;

import com.camilo.arce.proyecto.dto.ProviderDetailsDto;
import com.camilo.arce.proyecto.dto.ProvidersDto;

import static com.camilo.arce.proyecto.web.api.AuthApi.REDIRECT_URI;

public class ProviderRequestBuilder {
    public static String getUrl(ProviderDetailsDto details, String authEndpoint) {
        Long id = details.getProviders().getProviderId();
        ProvidersDto providers = details.getProviders();
        String url = authEndpoint + "?" +
                "redirect_uri=" + REDIRECT_URI + "&" +
                "client_id=" + providers.getClientId() + "&" +
                "state=" + id + "state" + "&" +
                "nonce=" + "nonce";

        if (details.getExtraScopes() != null) {
            url += "&scope=openid email " + details.getExtraScopes();
        } else {
            url += "&scope=openid email";
        }
        if (details.getResponseType() != null) {
            url += "&response_type=" + details.getResponseType();
        } else {
            url += "&response_type=code";
        }
        if (providers.getResponseMode() != null) {
            url += "&response_mode=" + providers.getResponseMode();
        } else {
            url += "&response_mode=form_post";
        }
        if (details.getDisplay() != null) {
            url += "&display=" + details.getDisplay();
        }
        if (details.getPrompt() != null) {
            url += "&prompt=" + details.getPrompt();
        }
        return url;
    }

}
