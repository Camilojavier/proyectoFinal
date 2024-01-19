package com.camilo.arce.proyecto.dto;

import com.camilo.arce.proyecto.dto.Messages.ProvidersMessages;
import jakarta.validation.constraints.NotBlank;



public class ProvidersDto implements ProvidersMessages {

    private Long providerId;

    @NotBlank(message = nameNotBlankMessage)
    private String name;

    @NotBlank(message = discoveryUrlNotBlankMessage)
    private String discoveryUrl;

    @NotBlank(message = clientIdNotBlankMessage)
    private String clientId;

    private String clientSecret;

    private String responseMode;

    private String tenantId;

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscoveryUrl() {
        return discoveryUrl;
    }

    public void setDiscoveryUrl(String discoveryUrl) {
        this.discoveryUrl = discoveryUrl;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getResponseMode() {
        return responseMode;
    }

    public void setResponseMode(String responseMode) {
        this.responseMode = responseMode;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}

