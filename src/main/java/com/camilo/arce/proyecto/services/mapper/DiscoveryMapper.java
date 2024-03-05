package com.camilo.arce.proyecto.services.mapper;

import com.camilo.arce.proyecto.domain.entities.Discovery;
import com.camilo.arce.proyecto.domain.entities.Providers;
import com.camilo.arce.proyecto.dto.DiscoveryDto;
import com.camilo.arce.proyecto.dto.ProvidersDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DiscoveryMapper implements CustomMapper<DiscoveryDto, Discovery> {

    private final ProvidersMapper providersMapper;

    @Override
    public DiscoveryDto toDto(Discovery discovery) {
        final DiscoveryDto discoveryDto = new DiscoveryDto();
        discoveryDto.setDiscoveryId(discovery.getDiscoveryId());
        discoveryDto.setIssuer(discovery.getIssuer());
        discoveryDto.setAuthEndpoint(discovery.getAuthEndpoint());
        discoveryDto.setTokenEndpoint(discovery.getTokenEndpoint());
        discoveryDto.setJwksUri(discovery.getJwksUri());
        if (discovery.getProviders() != null) {
            ProvidersDto providersDto = providersMapper.toDto(discovery.getProviders());
            discoveryDto.setProviders(providersDto);
        }
        return discoveryDto;
    }

    @Override
    public Discovery toEntity(DiscoveryDto discoveryDto) {
        final Discovery discovery = new Discovery();
        discovery.setDiscoveryId(discoveryDto.getDiscoveryId());
        discovery.setIssuer(discoveryDto.getIssuer());
        discovery.setAuthEndpoint(discoveryDto.getAuthEndpoint());
        discovery.setTokenEndpoint(discoveryDto.getTokenEndpoint());
        discovery.setJwksUri(discoveryDto.getJwksUri());
        if (discoveryDto.getProviders() != null) {
            final Providers providers = providersMapper.toEntity(discoveryDto.getProviders());
            discovery.setProviders(providers);
        }
        return discovery;
    }
}

