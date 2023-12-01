package com.camilo.arce.proyecto.dto.Messages;

public interface UsersMessages extends Messages{
    String userNameMessage = "El nombre de usuario no puede estar en blanco";
    String firstNameMessage = "El primer nombre no puede estar en blanco";
    String lastNameMessage = "El apellido no puede estar en blanco";
    String emailMessage = "El correo electrónico debe tener un formato válido";
    String passwordBlankMessage = "La contraseña no puede estar en blanco";
    int passwordMinSize = 8;
    String passwordSizeMessage =  "La contraseña debe tener al menos" +  passwordMinSize  + "caracteres";

}
