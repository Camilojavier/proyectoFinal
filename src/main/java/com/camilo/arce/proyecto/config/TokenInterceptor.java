package com.camilo.arce.proyecto.config;

import com.camilo.arce.proyecto.dto.IdTokenDto;
import com.camilo.arce.proyecto.dto.OpenIDUsersDto;
import com.camilo.arce.proyecto.dto.UsersDto;
import com.camilo.arce.proyecto.services.OpenIDUsersService;
import com.camilo.arce.proyecto.services.UsersService;
import com.camilo.arce.proyecto.tools.AuthTokenCrypt;
import com.camilo.arce.proyecto.web.api.AuthApi;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Objects;
import java.util.Optional;


@RequiredArgsConstructor
public class TokenInterceptor implements HandlerInterceptor {
    private final OpenIDUsersService openIDUsersService;
    private final UsersService usersService;
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(AuthApi.AUTH_TOKEN)) {
                    String token = cookie.getValue();
                    IdTokenDto idTokenDTO = AuthTokenCrypt.decryptIdTokenDTO(token);
                    if (idTokenDTO.isValidToken()) {
                        if (idTokenDTO.isWithOpenId()) {
                            Optional<OpenIDUsersDto> user = openIDUsersService.getOpenIDUserByDN(idTokenDTO.getAsDN());
                            if (user.isEmpty()) {
                                LOGGER.error("User with DN {} not found", idTokenDTO.getAsDN());
                                return false;
                            }
                        } else {
                            Optional<UsersDto> user = usersService.getUserByUsername(idTokenDTO.getCN());
                            if (user.isEmpty()) {
                                LOGGER.error("User with CN {} not found", idTokenDTO.getCN());
                                return false;
                            } else if (!Objects.equals(user.get().getEmail(), idTokenDTO.getE())) {
                                LOGGER.error("User with E {} not match", idTokenDTO.getE());
                                return false;
                            }
                        }
                        LOGGER.info("User with DN {} found", idTokenDTO.getAsDN());
                        Cookie newCookie = new Cookie(AuthApi.AUTH_TOKEN, AuthTokenCrypt.encryptIdTokenDTO(idTokenDTO));
                        newCookie.setPath("/");
                        newCookie.setMaxAge(600);
                        newCookie.setHttpOnly(true);
                        response.addCookie(newCookie);
                        return true;
                    }
                }
            }
        }
        // Si el usuario no está autenticado, establece el código de estado HTTP 401 (No autorizado)
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        // Opcional: Envía un mensaje de error en el cuerpo de la respuesta
        response.getWriter().write("Usuario no autenticado");
        return false;
    }
}
