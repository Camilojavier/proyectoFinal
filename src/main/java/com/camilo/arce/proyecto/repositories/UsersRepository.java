package com.camilo.arce.proyecto.repositories;

import com.camilo.arce.proyecto.domain.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users,Long> {
    Optional<Users> findByUsername(String username);
}
