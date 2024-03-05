package com.camilo.arce.proyecto.services.mapper;

import com.camilo.arce.proyecto.domain.entities.Roles;
import com.camilo.arce.proyecto.dto.RolesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RolesMapper implements CustomMapper<RolesDto, Roles> {

    @Override
    public RolesDto toDto(Roles role) {
        final RolesDto roleDto = new RolesDto();
        roleDto.setRoleId(role.getRoleId());
        roleDto.setName(role.getName());
        return roleDto;
    }

    @Override
    public Roles toEntity(RolesDto roleDto) {
        final Roles role = new Roles();
        role.setRoleId(roleDto.getRoleId());
        role.setName(roleDto.getName());
        return role;
    }
}
