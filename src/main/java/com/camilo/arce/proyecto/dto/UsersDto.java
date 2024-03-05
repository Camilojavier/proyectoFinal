package com.camilo.arce.proyecto.dto;

import com.camilo.arce.proyecto.dto.Messages.UsersMessages;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Data
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

}


