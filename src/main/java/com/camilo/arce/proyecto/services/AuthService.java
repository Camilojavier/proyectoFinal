package com.camilo.arce.proyecto.services;

import com.camilo.arce.proyecto.domain.entities.LoginRequest;
import com.camilo.arce.proyecto.dto.AuthResponseDTO;
import com.camilo.arce.proyecto.dto.UsersDto;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface AuthService {
    AuthResponseDTO login(LoginRequest request) throws JsonProcessingException;
    AuthResponseDTO register(UsersDto usersDto);
}
