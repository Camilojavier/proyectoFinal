package com.camilo.arce.proyecto.repositories;

import com.camilo.arce.proyecto.domain.entities.OpenIDUsers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpenIDUsersRepository extends JpaRepository<OpenIDUsers,Long> {
}
