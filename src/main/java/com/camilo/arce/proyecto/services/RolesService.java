package com.camilo.arce.proyecto.services;

import com.camilo.arce.proyecto.dto.RolesDTO;

import java.util.List;

public interface RolesService {

    RolesDTO getRoleById(Long roleId);

    List<RolesDTO> getAllRoles();

    RolesDTO createRole(RolesDTO rolesDTO);

    RolesDTO updateRole(Long roleId, RolesDTO rolesDTO);

    void deleteRole(Long roleId);
}

