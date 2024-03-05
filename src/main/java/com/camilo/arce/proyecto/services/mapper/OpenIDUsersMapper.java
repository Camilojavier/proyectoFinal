package com.camilo.arce.proyecto.services.mapper;

import com.camilo.arce.proyecto.domain.entities.OpenIDUsers;
import com.camilo.arce.proyecto.domain.entities.Providers;
import com.camilo.arce.proyecto.domain.entities.Users;
import com.camilo.arce.proyecto.dto.OpenIDUsersDto;
import com.camilo.arce.proyecto.dto.ProvidersDto;
import com.camilo.arce.proyecto.dto.UsersDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OpenIDUsersMapper implements CustomMapper<OpenIDUsersDto, OpenIDUsers> {
    private final UsersMapper usersMapper;
    private final ProvidersMapper providersMapper;

    @Override
    public OpenIDUsersDto toDto(OpenIDUsers openIDUsers) {
        final OpenIDUsersDto openIDUsersDto = new OpenIDUsersDto();
        openIDUsersDto.setOpenIdUsersId(openIDUsers.getOpenIdUsersId());
        openIDUsersDto.setSubjectId(openIDUsers.getSubjectId());
        openIDUsersDto.setMail(openIDUsers.getMail());
        openIDUsersDto.setIssuer(openIDUsers.getIssuer());
        openIDUsersDto.setOpenIdDN(openIDUsers.getOpenIdDN());
        // set other OpenIDUsers properties
        if (openIDUsers.getUsers() != null) {
            UsersDto usersDto = usersMapper.toDto(openIDUsers.getUsers());
            openIDUsersDto.setUsers(usersDto);
        }
        if (openIDUsers.getProviders() != null) {
            ProvidersDto providersDto = providersMapper.toDto(openIDUsers.getProviders());
            openIDUsersDto.setProviders(providersDto);
        }
        return openIDUsersDto;
    }

    @Override
    public OpenIDUsers toEntity(OpenIDUsersDto openIDUsersDto) {
        final OpenIDUsers openIDUsers = new OpenIDUsers();
        openIDUsers.setOpenIdUsersId(openIDUsersDto.getOpenIdUsersId());
        openIDUsers.setSubjectId(openIDUsersDto.getSubjectId());
        openIDUsers.setMail(openIDUsersDto.getMail());
        openIDUsers.setIssuer(openIDUsersDto.getIssuer());
        openIDUsers.setOpenIdDN(openIDUsersDto.getOpenIdDN());
        // set other OpenIDUsers properties
        if (openIDUsersDto.getUsers() != null) {
            Users users = usersMapper.toEntity(openIDUsersDto.getUsers());
            openIDUsers.setUsers(users);
        }
        if (openIDUsersDto.getProviders() != null) {
            final Providers providers = providersMapper.toEntity(openIDUsersDto.getProviders());
            openIDUsers.setProviders(providers);
        }
        return openIDUsers;
    }
}
