package com.camilo.arce.proyecto.services.mapper;

import com.camilo.arce.proyecto.domain.entities.Discovery;
import com.camilo.arce.proyecto.dto.DiscoveryDto;
import org.springframework.stereotype.Component;

@Component
public class DiscoveryMapper implements CustomMapper<DiscoveryDto, Discovery> {

    @Override
    public DiscoveryDto toDto(Discovery discovery) {
        final DiscoveryDto discoveryDto = new DiscoveryDto();
        discoveryDto.setDiscoveryId(discovery.getDiscoveryId());
        discoveryDto.setIssuer(discovery.getIssuer());
        discoveryDto.setAuthEndpoint(discovery.getAuthEndpoint());
        discoveryDto.setTokenEndpoint(discovery.getTokenEndpoint());
        discoveryDto.setJwksUri(discovery.getJwksUri());
        // set other Discovery properties
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
        // set other Discovery properties
        return discovery;
    }
}
