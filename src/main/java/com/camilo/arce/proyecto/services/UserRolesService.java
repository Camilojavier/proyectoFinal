package com.camilo.arce.proyecto.services;

import com.camilo.arce.proyecto.dto.RolesDto;
import com.camilo.arce.proyecto.dto.UserRolesDto;
import com.camilo.arce.proyecto.dto.UsersDto;

import java.util.List;

public interface UserRolesService {

    UserRolesDto getUserRoleById(Long userRoleId);

    List<UserRolesDto> getAllUserRoles();

    UserRolesDto createUserRole(UserRolesDto userRolesDTO);

    UserRolesDto updateUserRole(Long userRoleId, UserRolesDto userRolesDTO);

    void deleteUserRole(Long userRoleId);

    void deactivateUserRole(Long userRoleId);

    List<UserRolesDto> getUsersByRoleId(Long roleId);

    List<UserRolesDto> getRolesByUserId(Long userId);
}
