package com.camilo.arce.proyecto.repositories;

import com.camilo.arce.proyecto.domain.entities.Providers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProvidersRepository extends JpaRepository<Providers, Long> {
    Optional<Providers> findByName(String name);
}
