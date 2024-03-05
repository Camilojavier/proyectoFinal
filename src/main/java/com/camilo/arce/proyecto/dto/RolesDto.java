package com.camilo.arce.proyecto.dto;

import com.camilo.arce.proyecto.dto.Messages.RolesMessages;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RolesDto implements RolesMessages {

    private Long roleId;

    @NotBlank(message = nameMessage)
    private String name;

}

