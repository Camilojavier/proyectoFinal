package com.camilo.arce.proyecto.domain.entities;

import com.camilo.arce.proyecto.domain.annotations.DiscoveryAnnotations;
import jakarta.persistence.*;

@Entity
@Table(name = DiscoveryAnnotations.DISCOVERY)
public class Discovery implements DiscoveryAnnotations {

    @Id
    @SequenceGenerator(name = DISCOVERY_SEQUENCE, allocationSize = ALLOCATION_SIZE)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = DISCOVERY_SEQUENCE)
    @Column(name = DISCOVERY_ID)
    private Long discoveryId;

    private String issuer;

    @Column(name = AUTH_ENDPOINT)
    private String authEndpoint;

    @Column(name = TOKEN_ENDPOINT)
    private String tokenEndpoint;

    @Column(name = JWKS_URI)
    private String jwksUri;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = PROVIDER_ID)
    private Providers providers;

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

    public Providers getProviders() {
        return providers;
    }

    public void setProviders(Providers providers) {
        this.providers = providers;
    }
}

