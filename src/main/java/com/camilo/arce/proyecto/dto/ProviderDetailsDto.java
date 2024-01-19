package com.camilo.arce.proyecto.dto;

import com.camilo.arce.proyecto.dto.Messages.ProviderDetailsMessages;
import jakarta.validation.constraints.NotNull;


public class ProviderDetailsDto implements ProviderDetailsMessages {

    private Long providerDetailsId;

    private String scope;

    private String responseType;

    private String display;

    private String prompt;

    @NotNull(message = providersNotNullMessage)
    private ProvidersDto providers;

    public Long getProviderDetailsId() {
        return providerDetailsId;
    }

    public void setProviderDetailsId(Long providerDetailsId) {
        this.providerDetailsId = providerDetailsId;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public ProvidersDto getProviders() {
        return providers;
    }

    public void setProviders(ProvidersDto providers) {
        this.providers = providers;
    }
}
