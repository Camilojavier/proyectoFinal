package com.camilo.arce.proyecto.tool;

import com.camilo.arce.proyecto.dto.IdTokenDTO;
import com.camilo.arce.proyecto.dto.OpenIDResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TokenParser {
    public static IdTokenDTO parseIdToken(String token) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(token, IdTokenDTO.class);
        } catch (Exception e) {
            return null;
        }
    }
    public static OpenIDResponseDTO parseResponse(String token) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(token, OpenIDResponseDTO.class);
        } catch (Exception e) {
            return null;
        }
    }
}
