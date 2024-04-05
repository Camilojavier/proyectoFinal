package com.camilo.arce.proyecto.services.impl;

import com.camilo.arce.proyecto.domain.entities.LoginRequest;
import com.camilo.arce.proyecto.domain.entities.OpenIDRequest;
import com.camilo.arce.proyecto.domain.entities.UsernamePasswordRequest;
import com.camilo.arce.proyecto.dto.*;
import com.camilo.arce.proyecto.services.AuthService;
import com.camilo.arce.proyecto.tool.JwtUtils;
import com.camilo.arce.proyecto.tool.TokenParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    @Override
    public AuthResponseDTO login(LoginRequest request) throws JsonProcessingException {
        AuthResponseDTO authResponseDTO;
        if (request instanceof OpenIDRequest openIDRequest) {
            authResponseDTO = exchangeCode(openIDRequest);
            return authResponseDTO;
        } else if (request instanceof UsernamePasswordRequest) {
            UsernamePasswordRequest usernamePasswordRequest = (UsernamePasswordRequest) request;
        }
        return null;
    }

    private AuthResponseDTO exchangeCode(OpenIDRequest openIDRequest) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> body = openIDRequest.getRequestBody();
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);
        JsonNode jwksUri = objectMapper.readTree(restTemplate.getForObject(openIDRequest.getJwksUri(), String.class));
        JWKSet jwkSet = new JWKSet(jwksUri);
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    openIDRequest.getTokenEndpoint(),
                    HttpMethod.POST,
                    requestEntity,
                    String.class);

            OpenIDResponseDTO responseDTO = TokenParser.parseResponse(response.getBody());
            assert responseDTO != null;
            IdTokenDTO idTokenDTO = JwtUtils.parseAndVerifyJwt(responseDTO.getIdToken(), jwkSet , openIDRequest);

            if (response.getStatusCode() == HttpStatus.OK) {
                return AuthResponseDTO.success(idTokenDTO);
            } else {
                return AuthResponseDTO.error("Error occurred: " + response.getStatusCode(), response.getStatusCode().value());
            }
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            return AuthResponseDTO.error("HTTP error occurred: " + e.getStatusCode(), e.getStatusCode().value());
        } catch (RestClientException e) {
            return AuthResponseDTO.error("Rest client error occurred: " + e.getMessage(), -1);
        } catch (Exception e) {
            return AuthResponseDTO.error("Unexpected error occurred: " + e.getMessage(), -1);
        }
    }

    @Override
    public AuthResponseDTO register(UsersDto usersDto) {
        return null;
    }
}
