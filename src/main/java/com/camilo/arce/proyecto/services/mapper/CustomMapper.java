package com.camilo.arce.proyecto.services.mapper;

public interface CustomMapper<DTO, Entity> {
    DTO toDto(Entity entity);
    Entity toEntity(DTO dto);
}

