package com.camilo.arce.proyecto.dto;

import com.camilo.arce.proyecto.dto.Messages.AuthResponseMessages;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthResponseDto implements AuthResponseMessages {
    @NotNull(message = tokenNotNullMessage)
    private IdTokenDto token;
    private String error;
    private int httpStatusCode;
    public static AuthResponseDto success(IdTokenDto token) {
        AuthResponseDto response = new AuthResponseDto();
        response.setToken(token);
        return response;
    }
    public static AuthResponseDto error(String error, int httpStatusCode) {
        AuthResponseDto response = new AuthResponseDto();
        response.setError(error);
        response.setHttpStatusCode(httpStatusCode);
        return response;
    }

}
