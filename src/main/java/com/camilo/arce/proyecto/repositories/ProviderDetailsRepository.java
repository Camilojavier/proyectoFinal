package com.camilo.arce.proyecto.repositories;

import com.camilo.arce.proyecto.domain.entities.ProviderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProviderDetailsRepository extends JpaRepository<ProviderDetails,Long> {
    Optional<ProviderDetails> findByProviders_ProviderId(Long providers_id);

}
