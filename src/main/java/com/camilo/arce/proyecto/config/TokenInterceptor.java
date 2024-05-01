package com.camilo.arce.proyecto.config;

import com.camilo.arce.proyecto.dto.IdTokenDto;
import com.camilo.arce.proyecto.tools.CookieUtils;
import com.camilo.arce.proyecto.web.api.AuthApi;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(AuthApi.AUTH_TOKEN)) {
                    String token = cookie.getValue();
                    IdTokenDto idTokenDTO = CookieUtils.decryptIdTokenDTO(token);
                    if (idTokenDTO.isValidToken()) {
                        Cookie newCookie = new Cookie(AuthApi.AUTH_TOKEN, CookieUtils.encryptIdTokenDTO(idTokenDTO));
                        newCookie.setPath("/");
                        newCookie.setMaxAge(600);
                        newCookie.setHttpOnly(true);
                        response.addCookie(newCookie);
                        return true;
                    }
                }
            }
        }
        response.sendRedirect("/auth/oidc");
        return false;
    }
}
