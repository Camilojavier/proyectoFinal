package com.camilo.arce.proyecto.repositories;

import com.camilo.arce.proyecto.domain.entities.UserRoles;
import com.camilo.arce.proyecto.dto.UserRolesDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRolesRepository extends JpaRepository<UserRoles,Long> {
    List<UserRoles> findByRoles_RoleIdAndActiveIsTrue(Long roleId);

    List<UserRoles> findByUsers_UserIdAndActiveIsTrue(Long userId);
}
