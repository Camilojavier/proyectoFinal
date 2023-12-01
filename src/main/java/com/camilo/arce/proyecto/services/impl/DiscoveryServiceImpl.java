package com.camilo.arce.proyecto.services.impl;

import com.camilo.arce.proyecto.domain.entities.Discovery;
import com.camilo.arce.proyecto.dto.DiscoveryDTO;
import com.camilo.arce.proyecto.repositories.DiscoveryRepository;
import com.camilo.arce.proyecto.repositories.ProvidersRepository;
import com.camilo.arce.proyecto.services.DiscoveryService;
import com.camilo.arce.proyecto.services.mapper.DiscoveryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiscoveryServiceImpl implements DiscoveryService {

    private final DiscoveryRepository discoveryRepository;

    @Autowired
    public DiscoveryServiceImpl(DiscoveryRepository discoveryRepository, ProvidersRepository providersRepository) {
        this.discoveryRepository = discoveryRepository;
    }

    @Override
    public DiscoveryDTO getDiscoveryById(Long discoveryId) {
        Discovery discovery = discoveryRepository.findById(discoveryId)
                .orElseThrow(() -> new RuntimeException("Descubrimiento no encontrado con ID: " + discoveryId));
        return DiscoveryMapper.INSTANCE.toDto(discovery);
    }

    @Override
    public List<DiscoveryDTO> getAllDiscoveries() {
        List<Discovery> allDiscoveries = discoveryRepository.findAll();
        return allDiscoveries.stream()
                .map(DiscoveryMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DiscoveryDTO createDiscovery(DiscoveryDTO discoveryDTO) {
        Discovery newDiscovery = DiscoveryMapper.INSTANCE.toEntity(discoveryDTO);
        Discovery savedDiscovery = discoveryRepository.save(newDiscovery);
        return DiscoveryMapper.INSTANCE.toDto(savedDiscovery);
    }

    @Override
    public DiscoveryDTO updateDiscovery(Long discoveryId, DiscoveryDTO discoveryDTO) {
        Discovery existingDiscovery = discoveryRepository.findById(discoveryId)
                .orElseThrow(() -> new RuntimeException("Descubrimiento no encontrado con ID: " + discoveryId));

        if (discoveryDTO.getIssuer() != null && !discoveryDTO.getIssuer().isEmpty()) {
            existingDiscovery.setIssuer(discoveryDTO.getIssuer());
        }

        if (discoveryDTO.getAuthEndpoint() != null && !discoveryDTO.getAuthEndpoint().isEmpty()) {
            existingDiscovery.setAuthEndpoint(discoveryDTO.getAuthEndpoint());
        }

        if (discoveryDTO.getTokenEndpoint() != null && !discoveryDTO.getTokenEndpoint().isEmpty()) {
            existingDiscovery.setTokenEndpoint(discoveryDTO.getTokenEndpoint());
        }

        if (discoveryDTO.getJwksUri() != null && !discoveryDTO.getJwksUri().isEmpty()) {
            existingDiscovery.setJwksUri(discoveryDTO.getJwksUri());
        }

        Discovery updatedDiscovery = discoveryRepository.save(existingDiscovery);
        return DiscoveryMapper.INSTANCE.toDto(updatedDiscovery);
    }

    @Override
    public void deleteDiscovery(Long discoveryId) {
        discoveryRepository.deleteById(discoveryId);
    }
}
