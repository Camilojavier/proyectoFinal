package com.camilo.arce.proyecto.dto.Messages;

public interface DiscoveryMessages extends Messages{

    String issuerNotBlankMessage = "El emisor no puede estar en blanco";
    String authEndpointNotBlankMessage = "El punto de autenticación no puede estar en blanco";
    String tokenEndpointNotBlankMessage = "El punto de token no puede estar en blanco";
    String jwksUriNotBlankMessage = "La URI de JWKs no puede estar en blanco";
}
