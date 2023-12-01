package com.camilo.arce.proyecto.services;

import com.camilo.arce.proyecto.dto.OpenIDUsersDTO;

import java.util.List;

public interface OpenIDUsersService {

    OpenIDUsersDTO getOpenIDUserById(Long openIdUsersId);

    List<OpenIDUsersDTO> getAllOpenIDUsers();

    OpenIDUsersDTO createOpenIDUser(OpenIDUsersDTO openIDUsersDTO);

    OpenIDUsersDTO updateOpenIDUser(Long openIdUsersId, OpenIDUsersDTO openIDUsersDTO);

    void deleteOpenIDUser(Long openIdUsersId);
}
