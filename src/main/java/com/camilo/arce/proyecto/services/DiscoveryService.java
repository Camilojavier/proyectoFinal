package com.camilo.arce.proyecto.services;

import com.camilo.arce.proyecto.dto.DiscoveryDto;

import java.util.List;

public interface DiscoveryService {

    DiscoveryDto getDiscoveryById(Long discoveryId);

    List<DiscoveryDto> getAllDiscoveries();

    DiscoveryDto createDiscovery(DiscoveryDto discoveryDTO);

    DiscoveryDto updateDiscovery(Long discoveryId, DiscoveryDto discoveryDTO);

    void deleteDiscovery(Long discoveryId);
}
