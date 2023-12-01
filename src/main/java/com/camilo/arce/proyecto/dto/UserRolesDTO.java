package com.camilo.arce.proyecto.dto;

import com.camilo.arce.proyecto.dto.Messages.UserRolesMessages;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserRolesDTO implements UserRolesMessages {

    private Long userRolesId;

    @NotNull(message = userNotNullMessage)
    private UsersDTO users;

    @NotNull(message = roleNotNullMessage)
    private RolesDTO roles;

    private boolean active;
}

