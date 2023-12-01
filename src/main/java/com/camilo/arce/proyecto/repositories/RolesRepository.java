package com.camilo.arce.proyecto.repositories;

import com.camilo.arce.proyecto.domain.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Roles, Long> {
}
