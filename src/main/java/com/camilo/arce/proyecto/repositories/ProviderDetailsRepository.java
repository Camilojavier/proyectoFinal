package com.camilo.arce.proyecto.repositories;

import com.camilo.arce.proyecto.domain.entities.ProviderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderDetailsRepository extends JpaRepository<ProviderDetails,Long> {
}
