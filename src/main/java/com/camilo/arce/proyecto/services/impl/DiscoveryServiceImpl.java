package com.camilo.arce.proyecto.services.impl;

import com.camilo.arce.proyecto.domain.entities.Discovery;
import com.camilo.arce.proyecto.dto.DiscoveryDto;
import com.camilo.arce.proyecto.repositories.DiscoveryRepository;
import com.camilo.arce.proyecto.services.DiscoveryService;
import com.camilo.arce.proyecto.services.mapper.DiscoveryMapper;
import com.camilo.arce.proyecto.services.mapper.ProvidersMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class DiscoveryServiceImpl implements DiscoveryService {

    private final DiscoveryRepository discoveryRepository;
    private final DiscoveryMapper discoveryMapper;
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;
    private final ProvidersServiceImpl providersServiceImpl;

    @Override
    public Optional<DiscoveryDto> getDiscoveryById(Long discoveryId) {
        return discoveryRepository.findById(discoveryId).map(discoveryMapper::toDto);
    }

    @Override
    public List<DiscoveryDto> getAllDiscoveries() {
        List<Discovery> allDiscoveries = discoveryRepository.findAll();
        return allDiscoveries.stream()
                .map(discoveryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DiscoveryDto createDiscovery(DiscoveryDto discoveryDTO) {
        Discovery newDiscovery = discoveryMapper.toEntity(discoveryDTO);
        Discovery savedDiscovery = discoveryRepository.save(newDiscovery);
        return discoveryMapper.toDto(savedDiscovery);
    }

    @Override
    public DiscoveryDto updateDiscovery(Long discoveryId, DiscoveryDto discoveryDto) {
        Discovery existingDiscovery = discoveryRepository.findById(discoveryId)
                .orElseThrow(() -> new RuntimeException("Descubrimiento no encontrado con ID: " + discoveryId));

        if (discoveryDto.getIssuer() != null && !discoveryDto.getIssuer().isEmpty()) {
            existingDiscovery.setIssuer(discoveryDto.getIssuer());
        }
        if (discoveryDto.getAuthEndpoint() != null && !discoveryDto.getAuthEndpoint().isEmpty()) {
            existingDiscovery.setAuthEndpoint(discoveryDto.getAuthEndpoint());
        }

        if (discoveryDto.getTokenEndpoint() != null && !discoveryDto.getTokenEndpoint().isEmpty()) {
            existingDiscovery.setTokenEndpoint(discoveryDto.getTokenEndpoint());
        }

        if (discoveryDto.getJwksUri() != null && !discoveryDto.getJwksUri().isEmpty()) {
            existingDiscovery.setJwksUri(discoveryDto.getJwksUri());
        }

        Discovery updatedDiscovery = discoveryRepository.save(existingDiscovery);
        return discoveryMapper.toDto(updatedDiscovery);
    }

    @Override
    public void deleteDiscovery(Long discoveryId) {
        discoveryRepository.deleteById(discoveryId);
    }

    @Override
    public Optional<DiscoveryDto> getDiscoveryByProviderId(Long providerId) {
        return discoveryRepository.findByProviders_ProviderId(providerId).map(discoveryMapper::toDto);
    }

    @Override
    public DiscoveryDto discover(Long providerId, String discoveryUrl) throws JsonProcessingException {
        JsonNode discovery = objectMapper.readTree(restTemplate.getForObject(discoveryUrl, String.class));
        if (discovery.isEmpty()) {
            return null;
        }
        else {
            DiscoveryDto discoveryDto = new DiscoveryDto();
            discoveryDto.setIssuer(discovery.get("issuer").asText());
            discoveryDto.setAuthEndpoint(discovery.get("authorization_endpoint").asText());
            discoveryDto.setTokenEndpoint(discovery.get("token_endpoint").asText());
            discoveryDto.setJwksUri(discovery.get("jwks_uri").asText());
            discoveryDto.setProviders(providersServiceImpl.getProviderById(providerId).get());
            return createDiscovery(discoveryDto);
        }
    }
}