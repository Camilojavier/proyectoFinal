package com.camilo.arce.proyecto.config;

import com.camilo.arce.proyecto.dto.IdTokenDTO;
import com.camilo.arce.proyecto.tool.CookieUtils;
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
                if (cookie.getName().equals("auth_token")) {
                    String token = cookie.getValue();
                    IdTokenDTO idTokenDTO = CookieUtils.decryptIdTokenDTO(token);
                    if (idTokenDTO.isValidToken()) {
                        Cookie newCookie = new Cookie("auth_token", token);
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
