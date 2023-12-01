package com.camilo.arce.proyecto.repositories;

import com.camilo.arce.proyecto.domain.entities.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRolesRepository extends JpaRepository<UserRoles,Long> {
}
