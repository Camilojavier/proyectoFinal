package com.camilo.arce.proyecto.services;

import com.camilo.arce.proyecto.dto.ProvidersDto;

import java.util.List;

public interface ProvidersService {

    ProvidersDto getProviderById(Long providerId);

    List<ProvidersDto> getAllProviders();

    ProvidersDto createProvider(ProvidersDto providersDTO);

    ProvidersDto updateProvider(Long providerId, ProvidersDto providersDTO);

    void deleteProvider(Long providerId);
}
