package com.camilo.arce.proyecto.services;

import com.camilo.arce.proyecto.dto.OpenIDUsersDto;

import java.util.List;
import java.util.Optional;

public interface OpenIDUsersService {

    Optional<OpenIDUsersDto> getOpenIDUserById(Long openIdUsersId);

    List<OpenIDUsersDto> getAllOpenIDUsers();

    OpenIDUsersDto createOpenIDUser(OpenIDUsersDto openIDUsersDTO);

    OpenIDUsersDto updateOpenIDUser(Long openIdUsersId, OpenIDUsersDto openIDUsersDTO);

    void deleteOpenIDUser(Long openIdUsersId);

    boolean isRegisteredDN(String distinguishedName);
    Optional<OpenIDUsersDto> getOpenIDUserByDN(String distinguishedName);
    Optional<OpenIDUsersDto> getOpenIDUserByUserId(Long userId);
    Optional<OpenIDUsersDto> getOpenIDUserByProviderId(Long roleId);

}
