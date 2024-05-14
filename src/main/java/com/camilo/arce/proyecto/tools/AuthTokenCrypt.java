package com.camilo.arce.proyecto.tools;

import com.camilo.arce.proyecto.dto.IdTokenDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class AuthTokenCrypt {

    private static final String SECRET_KEY = generateSecureKey();
    private static final int IV_LENGTH = 16; // Tamaño del vector de inicialización en bytes



    public static String encryptIdTokenDTO(IdTokenDto idTokenDTO) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(idTokenDTO);

        byte[] iv = generateIV(); // Genera un IV único y aleatorio
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);

        byte[] encryptedBytes = cipher.doFinal(json.getBytes());

        // Concatena IV y texto cifrado y luego codifícalo en base64
        byte[] combined = new byte[IV_LENGTH + encryptedBytes.length];
        System.arraycopy(iv, 0, combined, 0, IV_LENGTH);
        System.arraycopy(encryptedBytes, 0, combined, IV_LENGTH, encryptedBytes.length);

        return Base64.getEncoder().encodeToString(combined);
    }

    public static IdTokenDto decryptIdTokenDTO(String encryptedToken) throws Exception {
        byte[] combined = Base64.getDecoder().decode(encryptedToken);
        byte[] iv = new byte[IV_LENGTH];
        byte[] encryptedBytes = new byte[combined.length - IV_LENGTH];
        System.arraycopy(combined, 0, iv, 0, IV_LENGTH);
        System.arraycopy(combined, IV_LENGTH, encryptedBytes, 0, encryptedBytes.length);

        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);

        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        String decryptedJson = new String(decryptedBytes);

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(decryptedJson, IdTokenDto.class);
    }
    private static String generateSecureKey() {
        // Genera una clave segura y aleatoria de 128 bits (16 bytes)
        SecureRandom secureRandom = new SecureRandom();
        byte[] key = new byte[16];
        secureRandom.nextBytes(key);
        return Base64.getEncoder().encodeToString(key);
    }

    private static byte[] generateIV() {
        // Genera un IV único y aleatorio de 128 bits (16 bytes)
        SecureRandom secureRandom = new SecureRandom();
        byte[] iv = new byte[IV_LENGTH];
        secureRandom.nextBytes(iv);
        return iv;
    }

}

