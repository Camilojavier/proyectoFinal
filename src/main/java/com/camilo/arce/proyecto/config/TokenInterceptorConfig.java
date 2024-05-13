package com.camilo.arce.proyecto.config;

import com.camilo.arce.proyecto.services.OpenIDUsersService;
import com.camilo.arce.proyecto.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class TokenInterceptorConfig {

    private final OpenIDUsersService openIDUsersService;
    private final UsersService usersService;

    @Bean
    public TokenInterceptor tokenInterceptor() {
        return new TokenInterceptor(openIDUsersService, usersService);
    }
}

