package com.camilo.arce.proyecto.services.impl;

import com.camilo.arce.proyecto.domain.entities.ProviderDetails;
import com.camilo.arce.proyecto.dto.ProviderDetailsDTO;
import com.camilo.arce.proyecto.repositories.ProviderDetailsRepository;
import com.camilo.arce.proyecto.repositories.ProvidersRepository;
import com.camilo.arce.proyecto.services.ProviderDetailsService;
import com.camilo.arce.proyecto.services.mapper.ProviderDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProviderDetailsServiceImpl implements ProviderDetailsService {

    private final ProviderDetailsRepository providerDetailsRepository;

    @Autowired
    public ProviderDetailsServiceImpl(ProviderDetailsRepository providerDetailsRepository, ProvidersRepository providersRepository) {
        this.providerDetailsRepository = providerDetailsRepository;
    }

    @Override
    public ProviderDetailsDTO getProviderDetailsById(Long providerDetailsId) {
        ProviderDetails providerDetails = providerDetailsRepository.findById(providerDetailsId)
                .orElseThrow(() -> new RuntimeException("Detalles del proveedor no encontrados con ID: " + providerDetailsId));
        return ProviderDetailsMapper.INSTANCE.toDto(providerDetails);
    }

    @Override
    public List<ProviderDetailsDTO> getAllProviderDetails() {
        List<ProviderDetails> allProviderDetails = providerDetailsRepository.findAll();
        return allProviderDetails.stream()
                .map(ProviderDetailsMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProviderDetailsDTO createProviderDetails(ProviderDetailsDTO providerDetailsDTO) {
        ProviderDetails newProviderDetails = ProviderDetailsMapper.INSTANCE.toEntity(providerDetailsDTO);
        ProviderDetails savedProviderDetails = providerDetailsRepository.save(newProviderDetails);
        return ProviderDetailsMapper.INSTANCE.toDto(savedProviderDetails);
    }

    @Override
    public ProviderDetailsDTO updateProviderDetails(Long providerDetailsId, ProviderDetailsDTO providerDetailsDTO) {
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
        return ProviderDetailsMapper.INSTANCE.toDto(updatedProviderDetails);
    }

    @Override
    public void deleteProviderDetails(Long providerDetailsId) {
        providerDetailsRepository.deleteById(providerDetailsId);
    }
}
