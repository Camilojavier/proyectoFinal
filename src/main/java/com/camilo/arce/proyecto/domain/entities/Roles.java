package com.camilo.arce.proyecto.domain.entities;

import com.camilo.arce.proyecto.domain.annotations.RolesAnnotations;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = RolesAnnotations.ROLES)
public class Roles implements RolesAnnotations{

    @Id
    @SequenceGenerator(name = ROLES_SEQUENCE, allocationSize = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ROLES_SEQUENCE)
    @Column(name = ROLE_ID)
    private Long roleId;

    private String name;

    @OneToMany(mappedBy = ROLES)
    private Set<UserRoles> userRoles;


}