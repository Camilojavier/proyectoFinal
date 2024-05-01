package com.camilo.arce.proyecto.tools;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;

public class SecureValueGenerator {
    public static String generateRandomValue(int length) {
        final SecureRandom random = new SecureRandom();
        final byte[] bytes = new byte[length];
        random.nextBytes(bytes);
        return bytesToHex(bytes);
    }

    private static String bytesToHex(byte[] bytes) {
        final StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }

    public static String generateSecureValue() {
        final int length = 16;
        return generateRandomValue(length);
    }
    public static String generateEncryptedSecureValue(){
        final String rawValue = generateSecureValue();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(rawValue);

    }
}
