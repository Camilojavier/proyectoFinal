package com.camilo.arce.proyecto.services.mapper;

import com.camilo.arce.proyecto.domain.entities.Discovery;
import com.camilo.arce.proyecto.dto.DiscoveryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DiscoveryMapper {

    DiscoveryMapper INSTANCE = Mappers.getMapper(DiscoveryMapper.class);

    DiscoveryDTO toDto(Discovery discovery);

    Discovery toEntity(DiscoveryDTO discoveryDTO);
}
