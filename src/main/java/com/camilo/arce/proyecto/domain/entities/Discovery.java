package com.camilo.arce.proyecto.domain.entities;

import com.camilo.arce.proyecto.domain.annotations.DiscoveryAnnotations;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
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

}

