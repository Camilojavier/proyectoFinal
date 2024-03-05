package com.camilo.arce.proyecto.domain.entities;

import com.camilo.arce.proyecto.domain.annotations.UsersAnnotations;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = UsersAnnotations.USERS)
public class Users implements UsersAnnotations {

    @Id
    @SequenceGenerator(name = USERS_SEQUENCE, allocationSize = ALLOCATION_SIZE)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = USERS_SEQUENCE)
    @Column(name = USER_ID)
    private Long userId;

    private String username;

    @Column(name = FIRST_NAME)
    private String firstName;

    @Column(name = LAST_NAME)
    private String lastName;

    private String email;

    @Column(name = HASHED_PASSWORD)
    private String hashedPassword;

    private String phone;

    @OneToMany(mappedBy = USERS, cascade = CascadeType.ALL)
    private Set<UserRoles> userRoles;

    @OneToMany(mappedBy = USERS, cascade = CascadeType.ALL)
    private Set<OpenIDUsers> openIDUsers;

}

