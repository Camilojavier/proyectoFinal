package com.camilo.arce.proyecto.domain.entities;

import com.camilo.arce.proyecto.domain.annotations.ProvidersAnnotations;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
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

    @OneToMany(mappedBy = PROVIDERS)
    private Set<OpenIDUsers> openIDUsers;

    @OneToOne(mappedBy = PROVIDERS, cascade = CascadeType.ALL, orphanRemoval = true)
    private ProviderDetails providerDetails;

    @OneToOne(mappedBy = PROVIDERS, cascade = CascadeType.ALL, orphanRemoval = true)
    private Discovery discovery;
}
