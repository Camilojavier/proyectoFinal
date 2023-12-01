package com.camilo.arce.proyecto.services.impl;

import com.camilo.arce.proyecto.domain.entities.Providers;
import com.camilo.arce.proyecto.dto.ProvidersDTO;
import com.camilo.arce.proyecto.repositories.ProvidersRepository;
import com.camilo.arce.proyecto.services.ProvidersService;
import com.camilo.arce.proyecto.services.mapper.ProvidersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProvidersServiceImpl implements ProvidersService {

    private final ProvidersRepository providersRepository;

    @Autowired
    public ProvidersServiceImpl(ProvidersRepository providersRepository) {
        this.providersRepository = providersRepository;
    }

    @Override
    public ProvidersDTO getProviderById(Long providerId) {
        Providers provider = providersRepository.findById(providerId)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con ID: " + providerId));
        return ProvidersMapper.INSTANCE.toDto(provider);
    }

    @Override
    public List<ProvidersDTO> getAllProviders() {
        List<Providers> allProviders = providersRepository.findAll();
        return allProviders.stream()
                .map(ProvidersMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProvidersDTO createProvider(ProvidersDTO providersDTO) {
        Providers newProvider = ProvidersMapper.INSTANCE.toEntity(providersDTO);
        Providers savedProvider = providersRepository.save(newProvider);
        return ProvidersMapper.INSTANCE.toDto(savedProvider);
    }

    @Override
    public ProvidersDTO updateProvider(Long providerId, ProvidersDTO providersDTO) {
        Providers existingProvider = providersRepository.findById(providerId)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con ID: " + providerId));

        if (providersDTO.getName() != null && !providersDTO.getName().isEmpty()) {
            existingProvider.setName(providersDTO.getName());
        }

        if (providersDTO.getDiscoveryUrl() != null && !providersDTO.getDiscoveryUrl().isEmpty()) {
            existingProvider.setDiscoveryUrl(providersDTO.getDiscoveryUrl());
        }

        if (providersDTO.getClientId() != null && !providersDTO.getClientId().isEmpty()) {
            existingProvider.setClientId(providersDTO.getClientId());
        }
        if (providersDTO.getClientSecret() != null && !providersDTO.getClientSecret().isEmpty()) {
            existingProvider.setClientSecret(providersDTO.getClientSecret());
        }
        if (providersDTO.getResponseMode() != null && !providersDTO.getResponseMode().isEmpty()) {
            existingProvider.setResponseMode(providersDTO.getResponseMode());
        }
        if (providersDTO.getTenantId() != null && !providersDTO.getTenantId().isEmpty()) {
            existingProvider.setTenantId(providersDTO.getTenantId());
        }

        Providers updatedProvider = providersRepository.save(existingProvider);
        return ProvidersMapper.INSTANCE.toDto(updatedProvider);
    }

    @Override
    public void deleteProvider(Long providerId) {
        providersRepository.deleteById(providerId);
    }
}
