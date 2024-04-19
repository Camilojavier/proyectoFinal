package com.camilo.arce.proyecto.web.rest;

import com.camilo.arce.proyecto.dto.ProvidersDto;
import com.camilo.arce.proyecto.services.ProvidersService;
import com.camilo.arce.proyecto.web.api.ProvidersApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(ProvidersApi.PROVIDERS_ROUTE)
public class ProvidersController implements  ProvidersApi{

    private final ProvidersService providersService;

    @GetMapping(PROVIDER_ID)
    public ResponseEntity<ProvidersDto> getProviderById(@PathVariable Long providerId) {
        Optional<ProvidersDto> providerDTO = providersService.getProviderById(providerId);
        return providerDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ProvidersDto>> getAllProviders() {
        List<ProvidersDto> providersDtoList = providersService.getAllProviders();
        return ResponseEntity.ok(providersDtoList);
    }

    @PostMapping
    public ResponseEntity<ProvidersDto> createProvider(@RequestBody ProvidersDto providersDTO) {
        ProvidersDto createdProviderDTO = providersService.createProvider(providersDTO);
        return new ResponseEntity<>(createdProviderDTO, HttpStatus.CREATED);
    }

    @PutMapping(PROVIDER_ID)
    public ResponseEntity<ProvidersDto> updateProvider(@PathVariable Long providerId, @RequestBody ProvidersDto providersDTO) {
        ProvidersDto updatedProviderDTO = providersService.updateProvider(providerId, providersDTO);
        return ResponseEntity.ok(updatedProviderDTO);
    }

    @DeleteMapping(PROVIDER_ID)
    public ResponseEntity<Void> deleteProvider(@PathVariable Long providerId) {
        providersService.deleteProvider(providerId);
        return ResponseEntity.noContent().build();
    }
}
