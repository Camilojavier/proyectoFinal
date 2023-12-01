package com.camilo.arce.proyecto.dto;

import com.camilo.arce.proyecto.dto.Messages.DiscoveryMessages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DiscoveryDTO implements DiscoveryMessages {

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
    private ProvidersDTO providers;
}
