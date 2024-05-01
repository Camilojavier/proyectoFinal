package com.camilo.arce.proyecto.web.rest;

import com.camilo.arce.proyecto.dto.ProviderDetailsDto;
import com.camilo.arce.proyecto.services.ProviderDetailsService;
import com.camilo.arce.proyecto.web.api.ProviderDetailsApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(ProviderDetailsApi.PROVIDER_DETAILS_ROUTE)
public class ProviderDetailsController implements  ProviderDetailsApi {

    private final ProviderDetailsService providerDetailsService;

    @GetMapping(PROVIDER_DETAILS_ID)
    public ResponseEntity<ProviderDetailsDto> getProviderDetailsById(@PathVariable Long providerDetailsId) {
        Optional<ProviderDetailsDto> providerDetailsDTO = providerDetailsService.getProviderDetailsById(providerDetailsId);
        return providerDetailsDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping(BY_PROVIDER_ID)
    public ResponseEntity<?> getProviderDetails(@PathVariable Long providerId) {
        Optional<ProviderDetailsDto> providerDetailsDto = providerDetailsService.getProviderDetailsByProviderId(providerId);
        return providerDetailsDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ProviderDetailsDto>> getAllProviderDetails() {
        List<ProviderDetailsDto> providerDetailsDtoList = providerDetailsService.getAllProviderDetails();
        return ResponseEntity.ok(providerDetailsDtoList);
    }


    @PostMapping
    public ResponseEntity<ProviderDetailsDto> createProviderDetails(@RequestBody ProviderDetailsDto providerDetailsDTO) {
        ProviderDetailsDto createdProviderDetailsDTO = providerDetailsService.createProviderDetails(providerDetailsDTO);
        return new ResponseEntity<>(createdProviderDetailsDTO, HttpStatus.CREATED);
    }

    @PutMapping(PROVIDER_DETAILS_ID)
    public ResponseEntity<ProviderDetailsDto> updateProviderDetails(@PathVariable Long providerDetailsId, @RequestBody ProviderDetailsDto providerDetailsDTO) {
        ProviderDetailsDto updatedProviderDetailsDTO = providerDetailsService.updateProviderDetails(providerDetailsId, providerDetailsDTO);
        return ResponseEntity.ok(updatedProviderDetailsDTO);
    }

    @DeleteMapping(PROVIDER_DETAILS_ID)
    public ResponseEntity<Void> deleteProviderDetails(@PathVariable Long providerDetailsId) {
        providerDetailsService.deleteProviderDetails(providerDetailsId);
        return ResponseEntity.noContent().build();
    }
}
