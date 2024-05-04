package com.camilo.arce.proyecto.web.rest;

import com.camilo.arce.proyecto.dto.DiscoveryDto;
import com.camilo.arce.proyecto.dto.ProvidersDto;
import com.camilo.arce.proyecto.services.DiscoveryService;
import com.camilo.arce.proyecto.services.ProvidersService;
import com.camilo.arce.proyecto.web.api.DiscoveryApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(DiscoveryApi.DISCOVERY_ROUTE)
public class DiscoveryController implements  DiscoveryApi{

    private final DiscoveryService discoveryService;
    private final ProvidersService providersService;

    @GetMapping(DISCOVERY_ID)
    public ResponseEntity<DiscoveryDto> getDiscoveryById(@PathVariable Long discoveryId) {
        Optional<DiscoveryDto> discoveryDTO = discoveryService.getDiscoveryById(discoveryId);
        return discoveryDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(BY_PROVIDER_ID)
    public ResponseEntity<DiscoveryDto> getDiscoveryByProviderId(@PathVariable Long providerId) {
        Optional<DiscoveryDto> discoveryDTO = discoveryService.getDiscoveryByProviderId(providerId);
        return discoveryDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<DiscoveryDto>> getAllDiscovery() {
        List<DiscoveryDto> discoveryDtoList = discoveryService.getAllDiscoveries();
        return ResponseEntity.ok(discoveryDtoList);
    }


    @PostMapping
    public ResponseEntity<DiscoveryDto> createDiscovery(@RequestBody DiscoveryDto discoveryDTO) {
        DiscoveryDto createdDiscoveryDTO = discoveryService.createDiscovery(discoveryDTO);
        return new ResponseEntity<>(createdDiscoveryDTO, HttpStatus.CREATED);
    }

    @PutMapping(DISCOVERY_ID)
    public ResponseEntity<DiscoveryDto> updateDiscovery(@PathVariable Long discoveryId, @RequestBody DiscoveryDto discoveryDTO) {
        DiscoveryDto updatedDiscoveryDTO = discoveryService.updateDiscovery(discoveryId, discoveryDTO);
        return ResponseEntity.ok(updatedDiscoveryDTO);
    }

    @DeleteMapping(DISCOVERY_ID)
    public ResponseEntity<Void> deleteDiscovery(@PathVariable Long discoveryId) {
        discoveryService.deleteDiscovery(discoveryId);
        return ResponseEntity.noContent().build();
    }
    @PostMapping(DISCOVER)
    public ResponseEntity<DiscoveryDto> discover(@RequestBody ProvidersDto partialProvidersDto) throws JsonProcessingException {
        Optional<ProvidersDto> optionalProvidersDto = providersService.getProviderByName(partialProvidersDto.getName());
        ProvidersDto providerDto = optionalProvidersDto.orElseThrow(() -> new RuntimeException("Provider not found"));
        DiscoveryDto discoveryDto = discoveryService.discover(providerDto.getProviderId(),partialProvidersDto.getDiscoveryUrl());
        if (discoveryDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(discoveryDto);
    }
}
