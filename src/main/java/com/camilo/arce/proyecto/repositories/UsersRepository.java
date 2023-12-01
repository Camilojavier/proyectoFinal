package com.camilo.arce.proyecto.repositories;

import com.camilo.arce.proyecto.domain.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users,Long> {
}
