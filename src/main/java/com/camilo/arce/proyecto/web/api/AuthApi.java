package com.camilo.arce.proyecto.web.api;

public interface AuthApi extends Api {
    String REDIRECT_URI = "http://localhost:8080/auth/oidc";
    String REFERER = "http://localhost:5173/";
    String AUTH_ROUTE =  "/auth";
    String LOGOUT_ROUTE =  "/logout";
    String LOGIN_ROUTE =  "/login";
    String OPENID_ROUTE = "/oidc";
    String CODE = "code";
    String STATE = "state";
}
