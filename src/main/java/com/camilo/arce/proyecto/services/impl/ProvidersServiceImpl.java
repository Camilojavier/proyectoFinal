package com.camilo.arce.proyecto.services.impl;

import com.camilo.arce.proyecto.domain.entities.Providers;
import com.camilo.arce.proyecto.dto.ProvidersDto;
import com.camilo.arce.proyecto.repositories.ProvidersRepository;
import com.camilo.arce.proyecto.services.ProvidersService;
import com.camilo.arce.proyecto.services.mapper.ProvidersMapper;  // Asegúrate de importar tu propio ProvidersMapper
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProvidersServiceImpl implements ProvidersService {

    private final ProvidersRepository providersRepository;
    private final ProvidersMapper providersMapper;

    @Autowired
    public ProvidersServiceImpl(ProvidersRepository providersRepository, ProvidersMapper providersMapper) {
        this.providersRepository = providersRepository;
        this.providersMapper = providersMapper;
    }

    @Override
    public Optional<ProvidersDto> getProviderById(Long providerId) {
        return providersRepository.findById(providerId)
                .map(providersMapper::toDto);
    }

    @Override
    public List<ProvidersDto> getAllProviders() {
        List<Providers> allProviders = providersRepository.findAll();
        return allProviders.stream()
                .map(providersMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProvidersDto createProvider(ProvidersDto providersDTO) {
        Providers newProvider = providersMapper.toEntity(providersDTO);
        Providers savedProvider = providersRepository.save(newProvider);
        return providersMapper.toDto(savedProvider);
    }

    @Override
    public ProvidersDto updateProvider(Long providerId, ProvidersDto providersDTO) {
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
        return providersMapper.toDto(updatedProvider);
    }

    @Override
    public void deleteProvider(Long providerId) {
        providersRepository.deleteById(providerId);
    }
}