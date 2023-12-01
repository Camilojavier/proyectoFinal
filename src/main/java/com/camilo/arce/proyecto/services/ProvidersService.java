package com.camilo.arce.proyecto.services;

import com.camilo.arce.proyecto.dto.ProvidersDTO;

import java.util.List;

public interface ProvidersService {

    ProvidersDTO getProviderById(Long providerId);

    List<ProvidersDTO> getAllProviders();

    ProvidersDTO createProvider(ProvidersDTO providersDTO);

    ProvidersDTO updateProvider(Long providerId, ProvidersDTO providersDTO);

    void deleteProvider(Long providerId);
}
