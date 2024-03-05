package com.camilo.arce.proyecto.dto;

import com.camilo.arce.proyecto.dto.Messages.UserRolesMessages;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRolesDto implements UserRolesMessages {

    private Long userRolesId;

    @NotNull(message = userNotNullMessage)
    private UsersDto users;

    @NotNull(message = roleNotNullMessage)
    private RolesDto roles;

    private boolean active;

}

