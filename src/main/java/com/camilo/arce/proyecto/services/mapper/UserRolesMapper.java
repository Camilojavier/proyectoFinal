package com.camilo.arce.proyecto.services.mapper;

import com.camilo.arce.proyecto.domain.entities.Roles;
import com.camilo.arce.proyecto.domain.entities.UserRoles;
import com.camilo.arce.proyecto.domain.entities.Users;
import com.camilo.arce.proyecto.dto.RolesDto;
import com.camilo.arce.proyecto.dto.UserRolesDto;
import com.camilo.arce.proyecto.dto.UsersDto;
import org.springframework.stereotype.Component;

@Component
public class UserRolesMapper implements CustomMapper<UserRolesDto, UserRoles> {

    @Override
    public UserRolesDto toDto(UserRoles userRole) {
        final UserRolesDto userRoleDto = new UserRolesDto();
        userRoleDto.setUserRolesId(userRole.getUserRolesId());
        userRoleDto.setActive(userRole.isActive());

        if (userRole.getUsers() != null) {
            UsersDto userDto = new UsersDto();
            Users user = userRole.getUsers();
            userDto.setUserId(user.getUserId());
            userDto.setUsername(user.getUsername());
            userDto.setFirstName(user.getFirstName());
            userDto.setLastName(user.getLastName());
            userDto.setEmail(user.getEmail());
            userDto.setPhone(user.getPhone());
            userRoleDto.setUsers(userDto);
        }

        if (userRole.getRoles() != null) {
            RolesDto roleDto = new RolesDto();
            Roles role = userRole.getRoles();
            roleDto.setRoleId(role.getRoleId());
            roleDto.setName(role.getName());
            userRoleDto.setRoles(roleDto);
        }

        // set other properties of UserRolesDto
        return userRoleDto;
    }

    @Override
    public UserRoles toEntity(UserRolesDto userRoleDto) {
        final UserRoles userRole = new UserRoles();
        userRole.setUserRolesId(userRoleDto.getUserRolesId());
        userRole.setActive(userRoleDto.isActive());


        if (userRoleDto.getUsers() != null) {
            Users user = new Users();
            UsersDto userDto = userRoleDto.getUsers();
            user.setUserId(userDto.getUserId());
            user.setUsername(userDto.getUsername());
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setEmail(userDto.getEmail());
            user.setPhone(userDto.getPhone());
            userRole.setUsers(user);
        }

        if (userRoleDto.getRoles() != null) {
            Roles role = new Roles();
            RolesDto roleDto = userRoleDto.getRoles();
            role.setRoleId(roleDto.getRoleId());
            role.setName(roleDto.getName());
            userRole.setRoles(role);
        }
        return userRole;
    }
}
