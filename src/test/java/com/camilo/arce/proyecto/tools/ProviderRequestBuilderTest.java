package com.camilo.arce.proyecto.tools;

import com.camilo.arce.proyecto.dto.ProviderDetailsDto;
import com.camilo.arce.proyecto.dto.ProvidersDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProviderRequestBuilderTest {

    @Test
    public void getUrlTest() {
        ProviderDetailsDto details = new ProviderDetailsDto();
        ProvidersDto provider = new ProvidersDto();
        provider.setProviderId(123L);
        provider.setClientId("testClientId");
        provider.setResponseMode("testResponseMode");
        details.setProviders(provider);
        details.setExtraScopes("testExtraScopes");
        details.setResponseType("testResponseType");
        details.setDisplay("testDisplay");
        details.setPrompt("testPrompt");
        String authEndpoint = "https://example.com/auth";

        String generatedUrl = ProviderRequestBuilder.getUrl(details, authEndpoint);
        String expectedUrl = "https://example.com/auth?redirect_uri=http://localhost:8080/auth/oidc&client_id=testClientId&state=123state&nonce=nonce&scope=openid emailtestExtraScopes&response_type=testResponseType&response_mode=testResponseMode&display=testDisplay&prompt=testPrompt";
        assertEquals(expectedUrl, generatedUrl);


    }
}
