package com.camilo.arce.proyecto.web.api;

public interface Api {
    String API_VERSION = "/v1";
    String USER_ID = "/{userId}";
    String ROLE_ID = "/{roleId}";
    String PROVIDER_ID = "/{providerId}";
    String AUTH_TOKEN = "auth_token";

    String BY_USER_ID = "/user" + USER_ID;
    String BY_PROVIDER_ID = "/provider" + PROVIDER_ID;

}
