package com.camilo.arce.proyecto.domain.entities;

import lombok.Data;
@Data
public class UsernamePasswordRequest implements LoginRequest {
    private String username;
    private String password;
}
