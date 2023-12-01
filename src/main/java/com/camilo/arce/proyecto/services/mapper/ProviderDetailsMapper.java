package com.camilo.arce.proyecto.services.mapper;

import com.camilo.arce.proyecto.domain.entities.ProviderDetails;
import com.camilo.arce.proyecto.dto.ProviderDetailsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProviderDetailsMapper {

    ProviderDetailsMapper INSTANCE = Mappers.getMapper(ProviderDetailsMapper.class);

    ProviderDetailsDTO toDto(ProviderDetails providerDetails);

    ProviderDetails toEntity(ProviderDetailsDTO providerDetailsDTO);
}
