package com.camilo.arce.proyecto.domain.entities;

import com.camilo.arce.proyecto.domain.annotations.ProvidersAnnotations;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name= ProvidersAnnotations.PROVIDERS)
public class Providers implements ProvidersAnnotations{

    @Id
    @SequenceGenerator(name = PROVIDERS_SEQUENCE, allocationSize = ALLOCATION_SIZE)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = PROVIDERS_SEQUENCE)
    @Column(name = PROVIDER_ID)
    private Long providerId;

    private String name;

    @Column(name = DISCOVERY_URL)
    private String discoveryUrl;

    @Column(name = CLIENT_ID)
    private String clientId;

    @Column(name = CLIENT_SECRET)
    private String clientSecret;

    @Column(name = RESPONSE_MODE)
    private String responseMode;

    @Column(name = TENANT_ID)
    private String tenantId;

    @OneToMany(mappedBy = PROVIDERS, cascade = CascadeType.ALL)
    private Set<OpenIDUsers> openIDUsers;

    @OneToMany(mappedBy = PROVIDERS, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProviderDetails> providerDetails;

    @OneToOne(mappedBy = PROVIDERS, cascade = CascadeType.ALL, orphanRemoval = true)
    private Discovery discovery;

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

    public Set<OpenIDUsers> getOpenIDUsers() {
        return openIDUsers;
    }

    public void setOpenIDUsers(Set<OpenIDUsers> openIDUsers) {
        this.openIDUsers = openIDUsers;
    }

    public Set<ProviderDetails> getProviderDetails() {
        return providerDetails;
    }

    public void setProviderDetails(Set<ProviderDetails> providerDetails) {
        this.providerDetails = providerDetails;
    }

    public Discovery getDiscovery() {
        return discovery;
    }

    public void setDiscovery(Discovery discovery) {
        this.discovery = discovery;
    }
}
