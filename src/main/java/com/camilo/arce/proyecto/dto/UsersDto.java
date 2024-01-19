package com.camilo.arce.proyecto.dto;

import com.camilo.arce.proyecto.dto.Messages.UsersMessages;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public class UsersDto implements UsersMessages, Serializable {

    private Long userId;

    @NotBlank(message = userNameMessage)
    private String username;

    @NotBlank(message = firstNameMessage)
    private String firstName;

    @NotBlank(message = lastNameMessage)
    private String lastName;

    @Email(message = emailMessage)
    private String email;

    @NotBlank(message = passwordBlankMessage)
    @Size(min = passwordMinSize, message = passwordSizeMessage)
    private String hashedPassword;

    private String phone;

    public UsersDto() {
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
}


