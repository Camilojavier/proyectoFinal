package com.camilo.arce.proyecto.services;

import com.camilo.arce.proyecto.dto.ProviderDetailsDTO;

import java.util.List;

public interface ProviderDetailsService {

    ProviderDetailsDTO getProviderDetailsById(Long providerDetailsId);

    List<ProviderDetailsDTO> getAllProviderDetails();

    ProviderDetailsDTO createProviderDetails(ProviderDetailsDTO providerDetailsDTO);

    ProviderDetailsDTO updateProviderDetails(Long providerDetailsId, ProviderDetailsDTO providerDetailsDTO);

    void deleteProviderDetails(Long providerDetailsId);
}
