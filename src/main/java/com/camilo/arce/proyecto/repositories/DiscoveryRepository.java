package com.camilo.arce.proyecto.repositories;

import com.camilo.arce.proyecto.domain.entities.Discovery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiscoveryRepository extends JpaRepository<Discovery,Long> {
    Optional<Discovery> findByProviders_ProviderId(Long providers_id);
}
