package com.camilo.arce.proyecto.services.impl;

import com.camilo.arce.proyecto.domain.entities.LoginRequest;
import com.camilo.arce.proyecto.domain.entities.OpenIDRequest;
import com.camilo.arce.proyecto.domain.entities.UsernamePasswordRequest;
import com.camilo.arce.proyecto.dto.*;
import com.camilo.arce.proyecto.services.AuthService;
import com.camilo.arce.proyecto.services.OpenIDUsersService;
import com.camilo.arce.proyecto.services.UsersService;
import com.camilo.arce.proyecto.tool.JwtUtils;
import com.camilo.arce.proyecto.tool.TokenParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final OpenIDUsersService openIDUsersService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UsersService usersService;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    @Override
    public AuthResponseDto login(LoginRequest request) throws JsonProcessingException {
        AuthResponseDto authResponseDTO;
        if (request instanceof OpenIDRequest openIDRequest) {
            authResponseDTO = exchangeCode(openIDRequest);
            return authResponseDTO;
        } else if (request instanceof UsernamePasswordRequest usernamePasswordRequest) {
            final String username = usernamePasswordRequest.getUsername();
            Optional<UsersDto> optUsersDto = usersService.getUserByUsername(username);
            if (optUsersDto.isPresent()) {
                UsersDto usersDto = optUsersDto.get();
                if (passwordEncoder.matches(usernamePasswordRequest.getPassword(),usersDto.getHashedPassword())) {
                    IdTokenDto idTokenDto = new IdTokenDto();
                    idTokenDto.setCN(username);
                    idTokenDto.setE(usersDto.getEmail());
                    idTokenDto.setWithOpenId(false);
                    return AuthResponseDto.success(idTokenDto);
                } else {
                    return AuthResponseDto.error("Wrong password or username", 403);
                }
            }else {
                return AuthResponseDto.error("User does not exist", 403);
            }
        }
        return AuthResponseDto.error("Invalid request", 404);
    }

    private AuthResponseDto exchangeCode(OpenIDRequest openIDRequest) throws JsonProcessingException {
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

            OpenIDResponseDto responseDTO = TokenParser.parseResponse(response.getBody());
            boolean isRegisteredDN = false;
            IdTokenDto idTokenDTO = null;
            if (responseDTO != null) {
                idTokenDTO = JwtUtils.parseAndVerifyJwt(responseDTO.getIdToken(), jwkSet, openIDRequest);

                if (idTokenDTO != null) {
                    isRegisteredDN = openIDUsersService.isRegisteredDN(idTokenDTO.getAsDN());
                }
            }
            if (response.getStatusCode() == HttpStatus.OK) {
                if (isRegisteredDN) {
                    return AuthResponseDto.success(idTokenDTO);
                } else {
                    return AuthResponseDto.error("OpenIDUser with credentials " + idTokenDTO.getAsDN() + " was not found",403);
                }
            } else {
                return AuthResponseDto.error("Error occurred: " + response.getStatusCode(), response.getStatusCode().value());
            }
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            return AuthResponseDto.error("HTTP error occurred: " + e.getStatusCode(), e.getStatusCode().value());
        } catch (RestClientException e) {
            return AuthResponseDto.error("Rest client error occurred: " + e.getMessage(), -1);
        } catch (Exception e) {
            return AuthResponseDto.error("Unexpected error occurred: " + e.getMessage(), -1);
        }
    }

    @Override
    public AuthResponseDto register(UsersDto usersDto) {
        return null;
    }
}
