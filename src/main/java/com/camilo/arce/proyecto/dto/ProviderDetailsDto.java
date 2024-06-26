package com.camilo.arce.proyecto.dto;

import com.camilo.arce.proyecto.dto.Messages.ProviderDetailsMessages;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
public class ProviderDetailsDto implements ProviderDetailsMessages {

    private Long providerDetailsId;

    private String extraScopes;

    private String responseType;

    private String display;

    private String prompt;

    @NotNull(message = providersNotNullMessage)
    private ProvidersDto providers;

}
