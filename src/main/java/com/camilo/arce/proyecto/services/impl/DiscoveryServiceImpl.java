package com.camilo.arce.proyecto.services.impl;

import com.camilo.arce.proyecto.domain.entities.Discovery;
import com.camilo.arce.proyecto.dto.DiscoveryDto;
import com.camilo.arce.proyecto.repositories.DiscoveryRepository;
import com.camilo.arce.proyecto.services.DiscoveryService;
import com.camilo.arce.proyecto.services.mapper.DiscoveryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiscoveryServiceImpl implements DiscoveryService {

    private final DiscoveryRepository discoveryRepository;
    private final DiscoveryMapper discoveryMapper;  // Añade tu propio DiscoveryMapper

    @Autowired
    public DiscoveryServiceImpl(DiscoveryRepository discoveryRepository, DiscoveryMapper discoveryMapper) {
        this.discoveryRepository = discoveryRepository;
        this.discoveryMapper = discoveryMapper;
    }

    @Override
    public DiscoveryDto getDiscoveryById(Long discoveryId) {
        Discovery discovery = discoveryRepository.findById(discoveryId)
                .orElseThrow(() -> new RuntimeException("Descubrimiento no encontrado con ID: " + discoveryId));
        return discoveryMapper.toDto(discovery);
    }

    @Override
    public List<DiscoveryDto> getAllDiscoveries() {
        List<Discovery> allDiscoveries = discoveryRepository.findAll();
        return allDiscoveries.stream()
                .map(discoveryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DiscoveryDto createDiscovery(DiscoveryDto discoveryDTO) {
        Discovery newDiscovery = discoveryMapper.toEntity(discoveryDTO);
        Discovery savedDiscovery = discoveryRepository.save(newDiscovery);
        return discoveryMapper.toDto(savedDiscovery);
    }

    @Override
    public DiscoveryDto updateDiscovery(Long discoveryId, DiscoveryDto discoveryDto) {
        Discovery existingDiscovery = discoveryRepository.findById(discoveryId)
                .orElseThrow(() -> new RuntimeException("Descubrimiento no encontrado con ID: " + discoveryId));

        if (discoveryDto.getIssuer() != null && !discoveryDto.getIssuer().isEmpty()) {
            existingDiscovery.setIssuer(discoveryDto.getIssuer());
        }
        if (discoveryDto.getAuthEndpoint() != null && !discoveryDto.getAuthEndpoint().isEmpty()) {
            existingDiscovery.setAuthEndpoint(discoveryDto.getAuthEndpoint());
        }

        if (discoveryDto.getTokenEndpoint() != null && !discoveryDto.getTokenEndpoint().isEmpty()) {
            existingDiscovery.setTokenEndpoint(discoveryDto.getTokenEndpoint());
        }

        if (discoveryDto.getJwksUri() != null && !discoveryDto.getJwksUri().isEmpty()) {
            existingDiscovery.setJwksUri(discoveryDto.getJwksUri());
        }

        Discovery updatedDiscovery = discoveryRepository.save(existingDiscovery);
        return discoveryMapper.toDto(updatedDiscovery);
    }

    @Override
    public void deleteDiscovery(Long discoveryId) {
        discoveryRepository.deleteById(discoveryId);
    }
}