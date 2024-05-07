package com.camilo.arce.proyecto.services.impl;

import com.camilo.arce.proyecto.domain.entities.Providers;
import com.camilo.arce.proyecto.dto.ProvidersDto;
import com.camilo.arce.proyecto.repositories.ProvidersRepository;
import com.camilo.arce.proyecto.services.ProvidersService;
import com.camilo.arce.proyecto.services.mapper.ProvidersMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProvidersServiceImpl implements ProvidersService {

    private final ProvidersRepository providersRepository;
    private final ProvidersMapper providersMapper;

    @Override
    public Optional<ProvidersDto> getProviderById(Long providerId) {
        return providersRepository.findById(providerId)
                .map(providersMapper::toDto);
    }

    @Override
    public Optional<ProvidersDto> getProviderByName(String name) {
        return providersRepository.findByName(name).map(providersMapper::toDto);
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
        checkOptionalNulls(providersDTO);
        Providers newProvider = providersMapper.toEntity(providersDTO);
        Providers savedProvider = providersRepository.save(newProvider);
        return providersMapper.toDto(savedProvider);
    }

    @Override
    public ProvidersDto updateProvider(Long providerId, ProvidersDto providersDTO) {
        Providers existingProvider = providersRepository.findById(providerId)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con ID: " + providerId));
        checkOptionalNulls(providersDTO);
        if (providersDTO.getName() != null && !providersDTO.getName().isEmpty()) {
            existingProvider.setName(providersDTO.getName());
        }
        if (providersDTO.getDiscoveryUrl() != null && !providersDTO.getDiscoveryUrl().isEmpty()) {
            existingProvider.setDiscoveryUrl(providersDTO.getDiscoveryUrl());
        }

        if (providersDTO.getClientId() != null && !providersDTO.getClientId().isEmpty()) {
            existingProvider.setClientId(providersDTO.getClientId());
        }
            existingProvider.setClientSecret(providersDTO.getClientSecret());
            existingProvider.setResponseMode(providersDTO.getResponseMode());
            existingProvider.setTenantId(providersDTO.getTenantId());
        Providers updatedProvider = providersRepository.save(existingProvider);
        return providersMapper.toDto(updatedProvider);
    }

    private void checkOptionalNulls(ProvidersDto providersDTO) {
        if (providersDTO.getClientSecret() == null || providersDTO.getClientSecret().isEmpty()){
            providersDTO.setClientSecret(null);
        }
        if(providersDTO.getResponseMode() == null || providersDTO.getResponseMode().isEmpty()){
            providersDTO.setResponseMode(null);
        }
        if(providersDTO.getTenantId() == null || providersDTO.getTenantId().isEmpty()){
            providersDTO.setTenantId(null);
        }
    }

    @Override
    public void deleteProvider(Long providerId) {
        providersRepository.deleteById(providerId);
    }
}