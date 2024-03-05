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
public class ProvidersController {

    private final ProvidersService providersService;

    @GetMapping(ProvidersApi.PROVIDERS_ID)
    public ResponseEntity<ProvidersDto> getProviderById(@PathVariable Long providersId) {
        Optional<ProvidersDto> providerDTO = providersService.getProviderById(providersId);
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

    @PutMapping(ProvidersApi.PROVIDERS_ID)
    public ResponseEntity<ProvidersDto> updateProvider(@PathVariable Long providersId, @RequestBody ProvidersDto providersDTO) {
        ProvidersDto updatedProviderDTO = providersService.updateProvider(providersId, providersDTO);
        return ResponseEntity.ok(updatedProviderDTO);
    }

    @DeleteMapping(ProvidersApi.PROVIDERS_ID)
    public ResponseEntity<Void> deleteProvider(@PathVariable Long providersId) {
        providersService.deleteProvider(providersId);
        return ResponseEntity.noContent().build();
    }
}
