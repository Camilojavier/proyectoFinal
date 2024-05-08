package com.camilo.arce.proyecto.dto;

import com.camilo.arce.proyecto.dto.Messages.ProvidersMessages;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
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

}

