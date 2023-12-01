package com.camilo.arce.proyecto.services.mapper;

import com.camilo.arce.proyecto.domain.entities.Roles;
import com.camilo.arce.proyecto.dto.RolesDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RolesMapper {

    RolesMapper INSTANCE = Mappers.getMapper(RolesMapper.class);

    RolesDTO toDto(Roles roles);

    Roles toEntity(RolesDTO rolesDTO);
}
