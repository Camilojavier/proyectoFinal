package com.camilo.arce.proyecto.services.mapper;

import com.camilo.arce.proyecto.domain.entities.ProviderDetails;
import com.camilo.arce.proyecto.domain.entities.Providers;
import com.camilo.arce.proyecto.dto.ProviderDetailsDto;
import com.camilo.arce.proyecto.dto.ProvidersDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProviderDetailsMapper implements CustomMapper<ProviderDetailsDto, ProviderDetails> {

    private final ProvidersMapper providersMapper;

    @Override
    public ProviderDetailsDto toDto(ProviderDetails providerDetails) {
        final ProviderDetailsDto providerDetailsDto = new ProviderDetailsDto();
        providerDetailsDto.setProviderDetailsId(providerDetails.getProviderDetailsId());
        providerDetailsDto.setScope(providerDetails.getScope());
        providerDetailsDto.setResponseType(providerDetails.getResponseType());
        providerDetailsDto.setDisplay(providerDetails.getDisplay());
        providerDetailsDto.setPrompt(providerDetails.getPrompt());
        // set other ProviderDetails properties
        if (providerDetails.getProviders() != null) {
            ProvidersDto providersDto = providersMapper.toDto(providerDetails.getProviders());
            providerDetailsDto.setProviders(providersDto);
        }
        return providerDetailsDto;
    }

    @Override
    public ProviderDetails toEntity(ProviderDetailsDto providerDetailsDto) {
        final ProviderDetails providerDetails = new ProviderDetails();
        providerDetails.setProviderDetailsId(providerDetailsDto.getProviderDetailsId());
        providerDetails.setScope(providerDetailsDto.getScope());
        providerDetails.setResponseType(providerDetailsDto.getResponseType());
        providerDetails.setDisplay(providerDetailsDto.getDisplay());
        providerDetails.setPrompt(providerDetailsDto.getPrompt());
        // set other ProviderDetails properties
        if (providerDetailsDto.getProviders() != null) {
            final Providers providers = providersMapper.toEntity(providerDetailsDto.getProviders());
            providerDetails.setProviders(providers);
        }
        return providerDetails;
    }
}
