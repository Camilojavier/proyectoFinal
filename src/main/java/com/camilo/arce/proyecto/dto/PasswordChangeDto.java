package com.camilo.arce.proyecto.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PasswordChangeDto {
    @NotBlank
    private String oldPassword;
    @NotBlank
    private String newPassword;
}
