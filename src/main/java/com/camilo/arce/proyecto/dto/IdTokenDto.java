package com.camilo.arce.proyecto.dto;

import com.camilo.arce.proyecto.dto.Messages.IdTokenMessages;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IdTokenDto implements IdTokenMessages {

    @NotBlank(message = cNNotBlankMessage)
    private String CN;
    private String DC;
    @NotBlank(message = eNotBlankMessage)
    private String E;
    private boolean withOpenId;
    private boolean validToken;
    private String asDN;


    public boolean isValidToken(){
        if (withOpenId && CN != null && DC != null && E != null) {
            validToken = true;
            return true;
        } else if (!withOpenId && CN != null && DC == null && E != null) {
            validToken = true;
            return true;
        }
        validToken = false;
        return false;
    }
    public String getAsDN(){
        return "CN="+ CN + ",DC=" + DC + ",E=" + E;
    }

}
