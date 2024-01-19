package com.camilo.arce.proyecto.domain.entities;

import com.camilo.arce.proyecto.domain.annotations.UserRolesAnnotations;
import jakarta.persistence.*;

@Entity
@Table(name = UserRolesAnnotations.USER_ROLES)
public class UserRoles implements UserRolesAnnotations {

    @Id
    @SequenceGenerator(name = USER_ROLES_SEQUENCE, allocationSize = ALLOCATION_SIZE)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = USER_ROLES_SEQUENCE)
    @Column(name = USER_ROLES_ID)
    private Long userRolesId;

    private boolean active;

    @ManyToOne
    @JoinColumn(name = USER_ID)
    private Users users;

    @ManyToOne
    @JoinColumn(name = ROLE_ID)
    private Roles roles;

    public Long getUserRolesId() {
        return userRolesId;
    }

    public void setUserRolesId(Long userRolesId) {
        this.userRolesId = userRolesId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }
}
