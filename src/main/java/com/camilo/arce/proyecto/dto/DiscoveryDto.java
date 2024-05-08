package com.camilo.arce.proyecto.dto;

import com.camilo.arce.proyecto.dto.Messages.DiscoveryMessages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
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

}
