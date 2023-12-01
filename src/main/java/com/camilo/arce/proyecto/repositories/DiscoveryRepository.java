package com.camilo.arce.proyecto.repositories;

import com.camilo.arce.proyecto.domain.entities.Discovery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscoveryRepository extends JpaRepository<Discovery,Long> {
}
