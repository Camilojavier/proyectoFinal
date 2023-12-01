package com.camilo.arce.proyecto.services.mapper;

import com.camilo.arce.proyecto.domain.entities.OpenIDUsers;
import com.camilo.arce.proyecto.dto.OpenIDUsersDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OpenIDUsersMapper {

    OpenIDUsersMapper INSTANCE = Mappers.getMapper(OpenIDUsersMapper.class);

    OpenIDUsersDTO toDto(OpenIDUsers openIDUsers);

    OpenIDUsers toEntity(OpenIDUsersDTO openIDUsersDTO);
}
