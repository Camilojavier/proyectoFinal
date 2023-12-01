package com.camilo.arce.proyecto.dto;

import com.camilo.arce.proyecto.dto.Messages.ProviderDetailsMessages;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProviderDetailsDTO implements ProviderDetailsMessages {

    private Long providerDetailsId;

    private String scope;

    private String responseType;

    private String display;

    private String prompt;

    @NotNull(message = providersNotNullMessage)
    private ProvidersDTO providers;
}
