package com.camilo.arce.proyecto.services;

import com.camilo.arce.proyecto.dto.UserRolesDTO;

import java.util.List;

public interface UserRolesService {

    UserRolesDTO getUserRoleById(Long userRoleId);

    List<UserRolesDTO> getAllUserRoles();

    UserRolesDTO createUserRole(UserRolesDTO userRolesDTO);

    UserRolesDTO updateUserRole(Long userRoleId, UserRolesDTO userRolesDTO);

    void deleteUserRole(Long userRoleId);

    void deactivateUserRole(Long userRoleId);
}
