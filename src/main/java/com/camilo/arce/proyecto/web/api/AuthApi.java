package com.camilo.arce.proyecto.web.api;

public interface AuthApi extends Api {
    String AUTH_ROUTE =  "/auth";
    String OPENID_ROUTE = "/oidc";
    String CODE = "code";
    String STATE = "state";
}
