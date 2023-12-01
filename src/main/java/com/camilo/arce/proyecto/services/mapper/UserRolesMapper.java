package com.camilo.arce.proyecto.services.mapper;

import com.camilo.arce.proyecto.domain.entities.UserRoles;
import com.camilo.arce.proyecto.dto.UserRolesDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserRolesMapper {

    UserRolesMapper INSTANCE = Mappers.getMapper(UserRolesMapper.class);

    UserRolesDTO toDto(UserRoles userRoles);

    UserRoles toEntity(UserRolesDTO userRolesDTO);
}
