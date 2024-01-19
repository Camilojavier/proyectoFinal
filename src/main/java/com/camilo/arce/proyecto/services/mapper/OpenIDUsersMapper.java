package com.camilo.arce.proyecto.services.mapper;

import com.camilo.arce.proyecto.domain.entities.OpenIDUsers;
import com.camilo.arce.proyecto.dto.OpenIDUsersDto;
import org.springframework.stereotype.Component;

@Component
public class OpenIDUsersMapper implements CustomMapper<OpenIDUsersDto, OpenIDUsers> {

    @Override
    public OpenIDUsersDto toDto(OpenIDUsers openIDUsers) {
        final OpenIDUsersDto openIDUsersDto = new OpenIDUsersDto();
        openIDUsersDto.setOpenIdUsersId(openIDUsers.getOpenIdUsersId());
        openIDUsersDto.setSubjectId(openIDUsers.getSubjectId());
        openIDUsersDto.setMail(openIDUsers.getMail());
        openIDUsersDto.setIssuer(openIDUsers.getIssuer());
        openIDUsersDto.setOpenIdDN(openIDUsers.getOpenIdDN());
        // set other OpenIDUsers properties
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
        return openIDUsers;
    }
}
