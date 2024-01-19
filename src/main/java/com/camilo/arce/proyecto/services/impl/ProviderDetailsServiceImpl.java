package com.camilo.arce.proyecto.services.impl;

import com.camilo.arce.proyecto.domain.entities.ProviderDetails;
import com.camilo.arce.proyecto.dto.ProviderDetailsDto;
import com.camilo.arce.proyecto.repositories.ProviderDetailsRepository;
import com.camilo.arce.proyecto.services.ProviderDetailsService;
import com.camilo.arce.proyecto.services.mapper.ProviderDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProviderDetailsServiceImpl implements ProviderDetailsService {

    private final ProviderDetailsRepository providerDetailsRepository;
    private final ProviderDetailsMapper providerDetailsMapper;

    @Autowired
    public ProviderDetailsServiceImpl(ProviderDetailsRepository providerDetailsRepository, ProviderDetailsMapper providerDetailsMapper) {
        this.providerDetailsRepository = providerDetailsRepository;
        this.providerDetailsMapper = providerDetailsMapper;
    }

    @Override
    public ProviderDetailsDto getProviderDetailsById(Long providerDetailsId) {
        ProviderDetails providerDetails = providerDetailsRepository.findById(providerDetailsId)
                .orElseThrow(() -> new RuntimeException("Detalles del proveedor no encontrados con ID: " + providerDetailsId));
        return providerDetailsMapper.toDto(providerDetails);
    }

    @Override
    public List<ProviderDetailsDto> getAllProviderDetails() {
        List<ProviderDetails> allProviderDetails = providerDetailsRepository.findAll();
        return allProviderDetails.stream()
                .map(providerDetailsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProviderDetailsDto createProviderDetails(ProviderDetailsDto providerDetailsDTO) {
        ProviderDetails newProviderDetails = providerDetailsMapper.toEntity(providerDetailsDTO);
        ProviderDetails savedProviderDetails = providerDetailsRepository.save(newProviderDetails);
        return providerDetailsMapper.toDto(savedProviderDetails);
    }

    @Override
    public ProviderDetailsDto updateProviderDetails(Long providerDetailsId, ProviderDetailsDto providerDetailsDTO) {
        ProviderDetails existingProviderDetails = providerDetailsRepository.findById(providerDetailsId)
                .orElseThrow(() -> new RuntimeException("Detalles del proveedor no encontrados con ID: " + providerDetailsId));

        if (providerDetailsDTO.getScope() != null && !providerDetailsDTO.getScope().isEmpty()) {
            existingProviderDetails.setScope(providerDetailsDTO.getScope());
        }
        if (providerDetailsDTO.getResponseType() != null && !providerDetailsDTO.getResponseType().isEmpty()) {
            existingProviderDetails.setResponseType(providerDetailsDTO.getResponseType());
        }

        if (providerDetailsDTO.getDisplay() != null && !providerDetailsDTO.getDisplay().isEmpty()) {
            existingProviderDetails.setDisplay(providerDetailsDTO.getDisplay());
        }

        if (providerDetailsDTO.getPrompt() != null && !providerDetailsDTO.getPrompt().isEmpty()) {
            existingProviderDetails.setPrompt(providerDetailsDTO.getPrompt());
        }

        ProviderDetails updatedProviderDetails = providerDetailsRepository.save(existingProviderDetails);
        return providerDetailsMapper.toDto(updatedProviderDetails);
    }

    @Override
    public void deleteProviderDetails(Long providerDetailsId) {
        providerDetailsRepository.deleteById(providerDetailsId);
    }
}



