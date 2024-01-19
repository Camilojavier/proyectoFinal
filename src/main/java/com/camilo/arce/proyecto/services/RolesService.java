package com.camilo.arce.proyecto.services;

import com.camilo.arce.proyecto.dto.RolesDto;

import java.util.List;
import java.util.Optional;

public interface RolesService {

    Optional<RolesDto> getRoleById(Long roleId);

    List<RolesDto> getAllRoles();

    RolesDto createRole(RolesDto rolesDTO);

    RolesDto updateRole(Long roleId, RolesDto rolesDTO);

    void deleteRole(Long roleId);
}

