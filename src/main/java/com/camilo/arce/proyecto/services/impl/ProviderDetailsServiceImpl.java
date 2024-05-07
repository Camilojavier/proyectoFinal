package com.camilo.arce.proyecto.services.impl;

import com.camilo.arce.proyecto.domain.entities.ProviderDetails;
import com.camilo.arce.proyecto.dto.ProviderDetailsDto;
import com.camilo.arce.proyecto.repositories.ProviderDetailsRepository;
import com.camilo.arce.proyecto.services.ProviderDetailsService;
import com.camilo.arce.proyecto.services.mapper.ProviderDetailsMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProviderDetailsServiceImpl implements ProviderDetailsService {

    private final ProviderDetailsRepository providerDetailsRepository;
    private final ProviderDetailsMapper providerDetailsMapper;

    @Override
    public Optional<ProviderDetailsDto> getProviderDetailsById(Long providerDetailsId) {
        return providerDetailsRepository.findById(providerDetailsId)
                .map(providerDetailsMapper::toDto);
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
        checkOptionalNulls(providerDetailsDTO);
        ProviderDetails newProviderDetails = providerDetailsMapper.toEntity(providerDetailsDTO);
        ProviderDetails savedProviderDetails = providerDetailsRepository.save(newProviderDetails);
        return providerDetailsMapper.toDto(savedProviderDetails);
    }

    @Override
    public ProviderDetailsDto updateProviderDetails(Long providerDetailsId, ProviderDetailsDto providerDetailsDTO) {
        ProviderDetails existingProviderDetails = providerDetailsRepository.findById(providerDetailsId)
                .orElseThrow(() -> new RuntimeException("Detalles del proveedor no encontrados con ID: " + providerDetailsId));
        checkOptionalNulls(providerDetailsDTO);
        existingProviderDetails.setExtraScopes(providerDetailsDTO.getExtraScopes());
        existingProviderDetails.setResponseType(providerDetailsDTO.getResponseType());
        existingProviderDetails.setDisplay(providerDetailsDTO.getDisplay());
        existingProviderDetails.setPrompt(providerDetailsDTO.getPrompt());

        ProviderDetails updatedProviderDetails = providerDetailsRepository.save(existingProviderDetails);
        return providerDetailsMapper.toDto(updatedProviderDetails);
    }
    private void checkOptionalNulls(ProviderDetailsDto providerDetailsDTO) {
        if (providerDetailsDTO.getExtraScopes() == null || providerDetailsDTO.getExtraScopes().isEmpty()){
            providerDetailsDTO.setExtraScopes(null);
        }
        if (providerDetailsDTO.getResponseType() == null || providerDetailsDTO.getResponseType().isEmpty()){
            providerDetailsDTO.setResponseType(null);
        }
        if (providerDetailsDTO.getPrompt() == null || providerDetailsDTO.getPrompt().isEmpty()){
            providerDetailsDTO.setPrompt(null);
        }
        if (providerDetailsDTO.getDisplay() == null || providerDetailsDTO.getDisplay().isEmpty()){
            providerDetailsDTO.setDisplay(null);
        }
    }

    @Override
    public void deleteProviderDetails(Long providerDetailsId) {
        providerDetailsRepository.deleteById(providerDetailsId);
    }

    @Override
    public Optional<ProviderDetailsDto> getProviderDetailsByProviderId(Long providerId) {
        return providerDetailsRepository.findByProviders_ProviderId(providerId).map(providerDetailsMapper::toDto);

    }
}



