package com.camilo.arce.proyecto.web.rest;

import com.camilo.arce.proyecto.dto.DiscoveryDto;
import com.camilo.arce.proyecto.services.DiscoveryService;
import com.camilo.arce.proyecto.web.api.DiscoveryApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(DiscoveryApi.DISCOVERY_ROUTE)
public class DiscoveryController {

    private final DiscoveryService discoveryService;

    @Autowired
    public DiscoveryController(DiscoveryService discoveryService) {
        this.discoveryService = discoveryService;
    }

    @GetMapping(DiscoveryApi.DISCOVERY_ID)
    public ResponseEntity<DiscoveryDto> getDiscoveryById(@PathVariable Long discoveryId) {
        Optional<DiscoveryDto> discoveryDTO = discoveryService.getDiscoveryById(discoveryId);
        return discoveryDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<DiscoveryDto>> getAllDiscoveries() {
        List<DiscoveryDto> discoveryDtoList = discoveryService.getAllDiscoveries();
        return ResponseEntity.ok(discoveryDtoList);
    }

    @PostMapping
    public ResponseEntity<DiscoveryDto> createDiscovery(@RequestBody DiscoveryDto discoveryDTO) {
        DiscoveryDto createdDiscoveryDTO = discoveryService.createDiscovery(discoveryDTO);
        return new ResponseEntity<>(createdDiscoveryDTO, HttpStatus.CREATED);
    }

    @PutMapping(DiscoveryApi.DISCOVERY_ID)
    public ResponseEntity<DiscoveryDto> updateDiscovery(@PathVariable Long discoveryId, @RequestBody DiscoveryDto discoveryDTO) {
        DiscoveryDto updatedDiscoveryDTO = discoveryService.updateDiscovery(discoveryId, discoveryDTO);
        return ResponseEntity.ok(updatedDiscoveryDTO);
    }

    @DeleteMapping(DiscoveryApi.DISCOVERY_ID)
    public ResponseEntity<Void> deleteDiscovery(@PathVariable Long discoveryId) {
        discoveryService.deleteDiscovery(discoveryId);
        return ResponseEntity.noContent().build();
    }
}
