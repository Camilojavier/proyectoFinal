package com.camilo.arce.proyecto.services;

import com.camilo.arce.proyecto.dto.DiscoveryDto;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Optional;

public interface DiscoveryService {

    Optional<DiscoveryDto> getDiscoveryById(Long discoveryId);

    List<DiscoveryDto> getAllDiscoveries();

    DiscoveryDto createDiscovery(DiscoveryDto discoveryDTO);

    DiscoveryDto updateDiscovery(Long discoveryId, DiscoveryDto discoveryDTO);

    void deleteDiscovery(Long discoveryId);

    Optional<DiscoveryDto> getDiscoveryByProviderId(Long providerId);
    DiscoveryDto discover(Long providerId, String discoveryUrl) throws JsonProcessingException;
}
