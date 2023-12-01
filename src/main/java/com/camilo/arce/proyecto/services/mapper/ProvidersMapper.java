package com.camilo.arce.proyecto.services.mapper;

import com.camilo.arce.proyecto.domain.entities.Providers;
import com.camilo.arce.proyecto.dto.ProvidersDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProvidersMapper {

    ProvidersMapper INSTANCE = Mappers.getMapper(ProvidersMapper.class);

    ProvidersDTO toDto(Providers providers);

    Providers toEntity(ProvidersDTO providersDTO);
}
