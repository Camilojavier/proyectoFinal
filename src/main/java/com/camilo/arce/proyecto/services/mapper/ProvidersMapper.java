package com.camilo.arce.proyecto.services.mapper;

import com.camilo.arce.proyecto.domain.entities.Providers;
import com.camilo.arce.proyecto.dto.ProvidersDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProvidersMapper implements CustomMapper<ProvidersDto, Providers> {

    @Override
    public ProvidersDto toDto(Providers provider) {
        final ProvidersDto providerDto = new ProvidersDto();
        providerDto.setProviderId(provider.getProviderId());
        providerDto.setName(provider.getName());
        providerDto.setDiscoveryUrl(provider.getDiscoveryUrl());
        providerDto.setClientId(provider.getClientId());
        providerDto.setClientSecret(provider.getClientSecret());
        providerDto.setResponseMode(provider.getResponseMode());
        providerDto.setTenantId(provider.getTenantId());
        // set other provider properties
        return providerDto;
    }

    @Override
    public Providers toEntity(ProvidersDto providerDto) {
        final Providers provider = new Providers();
        provider.setProviderId(providerDto.getProviderId());
        provider.setName(providerDto.getName());
        provider.setDiscoveryUrl(providerDto.getDiscoveryUrl());
        provider.setClientId(providerDto.getClientId());
        provider.setClientSecret(providerDto.getClientSecret());
        provider.setResponseMode(providerDto.getResponseMode());
        provider.setTenantId(providerDto.getTenantId());
        // set other provider properties
        return provider;
    }
}
