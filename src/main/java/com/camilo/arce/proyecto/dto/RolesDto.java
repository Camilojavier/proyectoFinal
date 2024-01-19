package com.camilo.arce.proyecto.dto;

import com.camilo.arce.proyecto.dto.Messages.RolesMessages;
import jakarta.validation.constraints.NotBlank;


public class RolesDto implements RolesMessages {

    private Long roleId;

    @NotBlank(message = nameMessage)
    private String name;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

