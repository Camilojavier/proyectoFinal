package com.camilo.arce.proyecto.dto;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class UsernamePasswordRequest implements LoginRequest {
    private String username;
    private String password;
}
