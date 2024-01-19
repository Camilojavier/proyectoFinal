package com.camilo.arce.proyecto.dto;

import com.camilo.arce.proyecto.dto.Messages.UserRolesMessages;
import jakarta.validation.constraints.NotNull;


public class UserRolesDto implements UserRolesMessages {

    private Long userRolesId;

    @NotNull(message = userNotNullMessage)
    private UsersDto users;

    @NotNull(message = roleNotNullMessage)
    private RolesDto roles;

    private boolean active;

    public Long getUserRolesId() {
        return userRolesId;
    }

    public void setUserRolesId(Long userRolesId) {
        this.userRolesId = userRolesId;
    }

    public UsersDto getUsers() {
        return users;
    }

    public void setUsers(UsersDto users) {
        this.users = users;
    }

    public RolesDto getRoles() {
        return roles;
    }

    public void setRoles(RolesDto roles) {
        this.roles = roles;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

