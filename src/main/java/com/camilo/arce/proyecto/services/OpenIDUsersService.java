package com.camilo.arce.proyecto.services;

import com.camilo.arce.proyecto.dto.OpenIDUsersDto;

import java.util.List;

public interface OpenIDUsersService {

    OpenIDUsersDto getOpenIDUserById(Long openIdUsersId);

    List<OpenIDUsersDto> getAllOpenIDUsers();

    OpenIDUsersDto createOpenIDUser(OpenIDUsersDto openIDUsersDTO);

    OpenIDUsersDto updateOpenIDUser(Long openIdUsersId, OpenIDUsersDto openIDUsersDTO);

    void deleteOpenIDUser(Long openIdUsersId);
}
