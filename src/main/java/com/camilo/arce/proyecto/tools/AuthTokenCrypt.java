package com.camilo.arce.proyecto.tools;

import com.camilo.arce.proyecto.dto.IdTokenDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AuthTokenCrypt {

    private static final String SECRET_KEY = SecureValueGenerator.generateSecureValue();



    public static String encryptIdTokenDTO(IdTokenDto idTokenDTO) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(idTokenDTO);

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(json.getBytes());

        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static IdTokenDto decryptIdTokenDTO(String encryptedToken) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
        SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedToken));
        String decryptedJson = new String(decryptedBytes);

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(decryptedJson, IdTokenDto.class);
    }
}

