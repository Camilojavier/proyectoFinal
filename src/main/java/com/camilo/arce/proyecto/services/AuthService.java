package com.camilo.arce.proyecto.services;

import com.camilo.arce.proyecto.domain.entities.LoginRequest;
import com.camilo.arce.proyecto.dto.AuthResponseDto;
import com.camilo.arce.proyecto.dto.UsersDto;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface AuthService {
    AuthResponseDto login(LoginRequest request) throws JsonProcessingException;
    AuthResponseDto register(UsersDto usersDto);
}
