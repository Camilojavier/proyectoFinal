package com.camilo.arce.proyecto.services;

import com.camilo.arce.proyecto.dto.DiscoveryDTO;

import java.util.List;

public interface DiscoveryService {

    DiscoveryDTO getDiscoveryById(Long discoveryId);

    List<DiscoveryDTO> getAllDiscoveries();

    DiscoveryDTO createDiscovery(DiscoveryDTO discoveryDTO);

    DiscoveryDTO updateDiscovery(Long discoveryId, DiscoveryDTO discoveryDTO);

    void deleteDiscovery(Long discoveryId);
}
