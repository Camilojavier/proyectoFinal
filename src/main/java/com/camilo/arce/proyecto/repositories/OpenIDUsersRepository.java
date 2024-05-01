package com.camilo.arce.proyecto.repositories;

import com.camilo.arce.proyecto.domain.entities.OpenIDUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OpenIDUsersRepository extends JpaRepository<OpenIDUsers,Long> {
    Optional<OpenIDUsers> findByOpenIdDN(String openIdDN);
    Optional<OpenIDUsers> findByUsers_UserId(Long userId);
    Optional<OpenIDUsers> findByProviders_ProviderId(Long providerId);
}
