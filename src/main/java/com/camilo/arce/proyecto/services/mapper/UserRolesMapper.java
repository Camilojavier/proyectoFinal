package com.camilo.arce.proyecto.services.mapper;

import com.camilo.arce.proyecto.domain.entities.Roles;
import com.camilo.arce.proyecto.domain.entities.UserRoles;
import com.camilo.arce.proyecto.domain.entities.Users;
import com.camilo.arce.proyecto.dto.RolesDto;
import com.camilo.arce.proyecto.dto.UserRolesDto;
import com.camilo.arce.proyecto.dto.UsersDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRolesMapper implements CustomMapper<UserRolesDto, UserRoles> {

    private final UsersMapper usersMapper;
    private final RolesMapper rolesMapper ;

    @Autowired
    public UserRolesMapper(UsersMapper usersMapper,RolesMapper rolesMapper) {
        this.usersMapper = usersMapper;
        this.rolesMapper = rolesMapper;
    }

    @Override
    public UserRolesDto toDto(UserRoles userRole) {
        final UserRolesDto userRoleDto = new UserRolesDto();
        userRoleDto.setUserRolesId(userRole.getUserRolesId());
        userRoleDto.setActive(userRole.isActive());
        if (userRole.getUsers() != null) {
            UsersDto usersDto = usersMapper.toDto(userRole.getUsers());
            userRoleDto.setUsers(usersDto);
        }
        if (userRole.getRoles() != null) {
            RolesDto rolesDto = rolesMapper.toDto(userRole.getRoles());
            userRoleDto.setRoles(rolesDto);
        }
        return userRoleDto;
    }

    @Override
    public UserRoles toEntity(UserRolesDto userRoleDto) {
        final UserRoles userRole = new UserRoles();
        userRole.setUserRolesId(userRoleDto.getUserRolesId());
        userRole.setActive(userRoleDto.isActive());
        if (userRoleDto.getUsers() != null) {
            Users users = usersMapper.toEntity(userRoleDto.getUsers());
            userRole.setUsers(users);
        }
        if (userRoleDto.getRoles() != null) {
            Roles roles = rolesMapper.toEntity(userRoleDto.getRoles());
            userRole.setRoles(roles);
        }
        return userRole;
    }
}
