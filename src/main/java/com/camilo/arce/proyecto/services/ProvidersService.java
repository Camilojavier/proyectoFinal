package com.camilo.arce.proyecto.services;

import com.camilo.arce.proyecto.dto.ProvidersDto;

import java.util.List;
import java.util.Optional;

public interface ProvidersService {

    Optional<ProvidersDto> getProviderById(Long providerId);

    Optional<ProvidersDto> getProviderByName(String name);

    List<ProvidersDto> getAllProviders();

    ProvidersDto createProvider(ProvidersDto providersDTO);

    ProvidersDto updateProvider(Long providerId, ProvidersDto providersDTO);

    void deleteProvider(Long providerId);
}
