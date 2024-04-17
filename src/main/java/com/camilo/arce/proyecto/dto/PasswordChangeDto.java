package com.camilo.arce.proyecto.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PasswordChangeDto {
    @NotBlank
    private String oldPassword;
    @NotBlank
    private String newPassword;
}
