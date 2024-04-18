package com.camilo.arce.proyecto.services;

import com.camilo.arce.proyecto.dto.DiscoveryDto;
import com.camilo.arce.proyecto.dto.ProviderDetailsDto;

import java.util.List;
import java.util.Optional;

public interface ProviderDetailsService {

    Optional<ProviderDetailsDto> getProviderDetailsById(Long providerDetailsId);

    List<ProviderDetailsDto> getAllProviderDetails();

    ProviderDetailsDto createProviderDetails(ProviderDetailsDto providerDetailsDTO);

    ProviderDetailsDto updateProviderDetails(Long providerDetailsId, ProviderDetailsDto providerDetailsDTO);

    void deleteProviderDetails(Long providerDetailsId);
    Optional<ProviderDetailsDto> getProviderDetailsByProviderId(Long providerId);

}
