package com.camilo.arce.proyecto.tools;

import com.camilo.arce.proyecto.dto.OpenIDResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TokenParser {

    public static OpenIDResponseDto parseResponse(String token) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(token, OpenIDResponseDto.class);
        } catch (Exception e) {
            return null;
        }
    }
}
