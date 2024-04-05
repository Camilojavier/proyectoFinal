package com.camilo.arce.proyecto.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Data
@AllArgsConstructor
public class OpenIDRequest implements LoginRequest{
    private String issuer;
    private String tokenEndpoint;
    private String code;
    private String state;
    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private String jwksUri;
    private String nonce;

    public MultiValueMap<String, String> getRequestBody() {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("code", getCode());
        body.add("client_id", getClientId());
        body.add("client_secret", getClientSecret());
        body.add("redirect_uri", getRedirectUri());
        return body;
    }
}
