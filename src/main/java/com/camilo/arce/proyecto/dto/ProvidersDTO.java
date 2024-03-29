package com.camilo.arce.proyecto.dto;

import com.camilo.arce.proyecto.dto.Messages.ProvidersMessages;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProvidersDTO implements ProvidersMessages {

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

