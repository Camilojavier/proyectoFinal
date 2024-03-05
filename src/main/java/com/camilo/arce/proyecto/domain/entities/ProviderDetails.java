package com.camilo.arce.proyecto.domain.entities;

import com.camilo.arce.proyecto.domain.annotations.ProviderDetailsAnnotations;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
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

}

