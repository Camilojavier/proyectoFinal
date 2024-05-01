package com.camilo.arce.proyecto.tools;

import com.camilo.arce.proyecto.domain.entities.OpenIDRequest;
import com.camilo.arce.proyecto.dto.IdTokenDto;
import com.camilo.arce.proyecto.dto.JWKSet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;

@RequiredArgsConstructor
public class JwtUtils {


    public static IdTokenDto parseAndVerifyJwt(String rawToken, JWKSet jwkSet, OpenIDRequest openIDRequest) throws JsonProcessingException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        ObjectMapper objectMapper = new ObjectMapper();

        String[] parts = rawToken.split("\\.");

        String header = decodeBase64(parts[0]);
        String payload = decodeBase64(parts[1]);
        byte[] signature = Base64.getUrlDecoder().decode(parts[2]);



        JsonNode jsonHeader = objectMapper.readTree(header);
        JsonNode jsonPayload = objectMapper.readTree(payload);

        boolean isValidPayload = checkPayload(jsonPayload, openIDRequest.getIssuer(), openIDRequest.getClientId(), openIDRequest.getNonce());
        boolean isValidSignature = checkSignature(jwkSet, jsonHeader, parts[0] + "." + parts[1], signature, openIDRequest.getClientSecret());

        if(!isValidPayload || !isValidSignature) {
            return null;
        }
        IdTokenDto idTokenDTO = new IdTokenDto();
        idTokenDTO.setCN(jsonPayload.get("sub").asText());
        idTokenDTO.setDC(openIDRequest.getName());
        idTokenDTO.setE(jsonPayload.get("email").asText());
        idTokenDTO.setWithOpenId(true);

        return idTokenDTO;
    }

    private static boolean checkSignature(JWKSet jwkSet, JsonNode jsonHeader, String data, byte[] signature, String clientSecret) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        final String sigAlg = jsonHeader.get("alg").asText();
        switch (sigAlg) {
            case "RS256":
                final Signature sig = Signature.getInstance("SHA256withRSA");
                sig.initVerify(jwkSet.getKey(jsonHeader.get("kid").asText()));
                sig.update(data.getBytes(StandardCharsets.US_ASCII));
                return sig.verify(signature);
            case "HS256":
                final Mac mac = Mac.getInstance("HmacSHA256");
                mac.init(new SecretKeySpec(Base64.getUrlDecoder().decode(clientSecret),"HmacSHA256"));
                mac.update(data.getBytes(StandardCharsets.US_ASCII));
                final byte[] genSig = mac.doFinal();
                return Arrays.equals(genSig, signature);
            default:
                return false;
        }
    }

    private static boolean checkPayload(JsonNode jsonPayload, String issuer, String clientId, String nonce) {
        final String iss = jsonPayload.get("iss").asText();
        if (iss == null || iss.isEmpty() || !iss.contains(issuer))  {
            return false;
        }
        final JsonNode aud = jsonPayload.get("aud");
        boolean audMatch = false;
        if (aud.isArray()) {
            for (JsonNode audNode : aud) {
                if (audNode.asText().equals(clientId)){
                    audMatch = true;
                }
            }
            if (!audMatch) return false;
        } else {
            if (!aud.asText().equals(clientId)) {
                return false;
            }
        }
        final long exp = jsonPayload.get("exp").asLong();
        if (exp * 1000L <= System.currentTimeMillis()) return false;
        final String receivedNonce = jsonPayload.get("nonce").asText();
        return Objects.equals(receivedNonce, nonce);
    }


    private static String decodeBase64(String encoded) {
        byte[] decodedBytes = Base64.getUrlDecoder().decode(encoded);
        return new String(decodedBytes, StandardCharsets.UTF_8);
    }
}
