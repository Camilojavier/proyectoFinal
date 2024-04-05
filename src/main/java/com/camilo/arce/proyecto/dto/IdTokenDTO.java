package com.camilo.arce.proyecto.dto;

import com.camilo.arce.proyecto.dto.Messages.IdTokenMessages;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class IdTokenDTO implements IdTokenMessages {

    @NotBlank(message = cNNotBlankMessage)
    private String CN;
    private String DC;
    @NotBlank(message = eNotBlankMessage)
    private String E;
    private boolean withOpenId;
    private boolean validToken;


    public boolean isValidToken(){
        if (withOpenId && CN != null && DC != null && E != null) {
            validToken = true;
            return true;
        }
        else {
            validToken = false;
            return !withOpenId && CN != null && DC == null && E != null;
        }
    }

}
