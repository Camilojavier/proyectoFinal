package com.camilo.arce.proyecto.dto;

import com.camilo.arce.proyecto.dto.Messages.AuthResponseMessages;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AuthResponseDTO implements AuthResponseMessages {
    @NotNull(message = tokenNotNullMessage)
    private IdTokenDTO token;
    private String error;
    private int httpStatusCode;
    public static AuthResponseDTO success(IdTokenDTO token) {
        AuthResponseDTO response = new AuthResponseDTO();
        response.setToken(token);
        return response;
    }
    public static AuthResponseDTO error(String error, int httpStatusCode) {
        AuthResponseDTO response = new AuthResponseDTO();
        response.setError(error);
        response.setHttpStatusCode(httpStatusCode);
        return response;
    }

}
