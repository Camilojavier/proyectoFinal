package com.camilo.arce.proyecto.tools;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SecureValueGeneratorTest {
    @Test
    public void testGenerateSecureValue() throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String value = SecureValueGenerator.generateSecureValue();
        String encodedValue = passwordEncoder.encode(value);
        assertTrue(passwordEncoder.matches(value, encodedValue));
    }
}
