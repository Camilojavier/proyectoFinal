package com.camilo.arce.proyecto.domain.entities;

import com.camilo.arce.proyecto.domain.annotations.RolesAnnotations;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = RolesAnnotations.ROLES)
public class Roles implements RolesAnnotations{

    @Id
    @SequenceGenerator(name = ROLES_SEQUENCE, allocationSize = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ROLES_SEQUENCE)
    @Column(name = ROLE_ID)
    private Long roleId;

    private String name;

    @OneToMany(mappedBy = ROLES, cascade = CascadeType.ALL)
    private Set<UserRoles> userRoles;

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

    public Set<UserRoles> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRoles> userRoles) {
        this.userRoles = userRoles;
    }
}