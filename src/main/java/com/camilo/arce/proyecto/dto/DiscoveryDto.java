package com.camilo.arce.proyecto.dto;

import com.camilo.arce.proyecto.dto.Messages.DiscoveryMessages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class DiscoveryDto implements DiscoveryMessages {

    private Long discoveryId;

    @NotBlank(message = issuerNotBlankMessage)
    private String issuer;

    @NotBlank(message = authEndpointNotBlankMessage)
    private String authEndpoint;

    @NotBlank(message = tokenEndpointNotBlankMessage)
    private String tokenEndpoint;

    @NotBlank(message = jwksUriNotBlankMessage)
    private String jwksUri;

    @NotNull(message = providersNotNullMessage)
    private ProvidersDto providers;

    public Long getDiscoveryId() {
        return discoveryId;
    }

    public void setDiscoveryId(Long discoveryId) {
        this.discoveryId = discoveryId;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getAuthEndpoint() {
        return authEndpoint;
    }

    public void setAuthEndpoint(String authEndpoint) {
        this.authEndpoint = authEndpoint;
    }

    public String getTokenEndpoint() {
        return tokenEndpoint;
    }

    public void setTokenEndpoint(String tokenEndpoint) {
        this.tokenEndpoint = tokenEndpoint;
    }

    public String getJwksUri() {
        return jwksUri;
    }

    public void setJwksUri(String jwksUri) {
        this.jwksUri = jwksUri;
    }

    public ProvidersDto getProviders() {
        return providers;
    }

    public void setProviders(ProvidersDto providers) {
        this.providers = providers;
    }
}
