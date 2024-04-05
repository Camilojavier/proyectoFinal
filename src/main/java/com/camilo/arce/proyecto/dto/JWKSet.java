package com.camilo.arce.proyecto.dto;

import com.fasterxml.jackson.databind.JsonNode;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class JWKSet {

    private final Map<String, PublicKey> keys;

    public JWKSet(JsonNode jwkSet){
        keys = new HashMap<>();
        final KeyFactory keyFactory;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
        } catch (final NoSuchAlgorithmException e) {
            throw new RuntimeException("Platform does not support RSA.", e);
        }
        try {
            final JsonNode keysProp  = jwkSet.get("keys");
            final Base64.Decoder decoder = Base64.getUrlDecoder();
            for (int i = 0; i < keysProp .size(); i++) {
                final JsonNode keyDef = keysProp .get(i);
                if(keyDef.get("kty").asText().equals("RSA") && keyDef.get("use").asText().equals("sig")){
                keys.put(keyDef.get("kid").asText(), keyFactory.generatePublic(new RSAPublicKeySpec(new BigInteger(1,decoder.decode(keyDef.get("n").asText().getBytes(StandardCharsets.US_ASCII))),new BigInteger(1, decoder.decode(keyDef.get("e").asText().getBytes(StandardCharsets.US_ASCII))))));
                }
            }
        } catch (final InvalidKeySpecException e) {
            throw new IllegalArgumentException("Invalid key specification.", e);
        }

    }

    public PublicKey getKey(final String kid) {

        final PublicKey key = this.keys.get(kid);
        if (key == null)
            throw new IllegalArgumentException("Unknown key ID: " + kid);

        return key;
    }
}
