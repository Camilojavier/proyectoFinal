package com.camilo.arce.proyecto.services;

import com.camilo.arce.proyecto.dto.ProviderDetailsDto;

import java.util.List;

public interface ProviderDetailsService {

    ProviderDetailsDto getProviderDetailsById(Long providerDetailsId);

    List<ProviderDetailsDto> getAllProviderDetails();

    ProviderDetailsDto createProviderDetails(ProviderDetailsDto providerDetailsDTO);

    ProviderDetailsDto updateProviderDetails(Long providerDetailsId, ProviderDetailsDto providerDetailsDTO);

    void deleteProviderDetails(Long providerDetailsId);
}
