package com.camilo.arce.proyecto.domain.entities;

import com.camilo.arce.proyecto.domain.annotations.ProviderDetailsAnnotations;
import jakarta.persistence.*;


@Entity
@Table(name = ProviderDetailsAnnotations.PROVIDER_DETAILS)
public class ProviderDetails implements ProviderDetailsAnnotations {

    @Id
    @SequenceGenerator(name = PROVIDER_DETAILS_SEQUENCE, allocationSize = ALLOCATION_SIZE)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = PROVIDER_DETAILS_SEQUENCE)
    @Column(name = PROVIDER_DETAILS_ID)
    private Long providerDetailsId;

    private String scope;

    @Column(name = RESPONSE_TYPE)
    private String responseType;

    private String display;

    private String prompt;

    @ManyToOne
    @JoinColumn(name = PROVIDER_ID)
    private Providers providers;

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

    public Providers getProviders() {
        return providers;
    }

    public void setProviders(Providers providers) {
        this.providers = providers;
    }
}

