package com.camilo.arce.proyecto.services;

import com.camilo.arce.proyecto.dto.AuthResponseDto;
import com.camilo.arce.proyecto.dto.LoginRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface AuthService {
    AuthResponseDto login(LoginRequest request) throws JsonProcessingException;
}
