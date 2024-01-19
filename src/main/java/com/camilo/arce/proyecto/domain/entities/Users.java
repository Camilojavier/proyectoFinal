package com.camilo.arce.proyecto.domain.entities;

import com.camilo.arce.proyecto.domain.annotations.UsersAnnotations;
import jakarta.persistence.*;

import java.util.Set;

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

    public Users(Long userId, String username, String firstName, String lastName, String email, String hashedPassword, String phone) {
        this.userId = userId;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.phone = phone;
    }

    public Users() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<UserRoles> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRoles> userRoles) {
        this.userRoles = userRoles;
    }

    public Set<OpenIDUsers> getOpenIDUsers() {
        return openIDUsers;
    }

    public void setOpenIDUsers(Set<OpenIDUsers> openIDUsers) {
        this.openIDUsers = openIDUsers;
    }
}

