package com.camilo.arce.proyecto.tool;

import com.camilo.arce.proyecto.dto.IdTokenDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class CookieUtils {

    private static final String SECRET_KEY = "tu_clave_secreta_para_el_cifrado";

    public static String encryptIdTokenDTO(IdTokenDTO idTokenDTO) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(idTokenDTO);

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(json.getBytes());

        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static IdTokenDTO decryptIdTokenDTO(String encryptedToken) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
        SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedToken));
        String decryptedJson = new String(decryptedBytes);

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(decryptedJson, IdTokenDTO.class);
    }
}

