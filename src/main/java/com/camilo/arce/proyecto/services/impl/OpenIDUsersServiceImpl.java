package com.camilo.arce.proyecto.services.impl;

import com.camilo.arce.proyecto.domain.entities.OpenIDUsers;
import com.camilo.arce.proyecto.dto.OpenIDUsersDto;
import com.camilo.arce.proyecto.repositories.OpenIDUsersRepository;
import com.camilo.arce.proyecto.services.OpenIDUsersService;
import com.camilo.arce.proyecto.services.mapper.OpenIDUsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OpenIDUsersServiceImpl implements OpenIDUsersService {

    private final OpenIDUsersRepository openIDUsersRepository;
    private final OpenIDUsersMapper openIDUsersMapper;  // Añade tu propio OpenIDUsersMapper

    @Autowired
    public OpenIDUsersServiceImpl(OpenIDUsersRepository openIDUsersRepository, OpenIDUsersMapper openIDUsersMapper) {
        this.openIDUsersRepository = openIDUsersRepository;
        this.openIDUsersMapper = openIDUsersMapper;
    }

    @Override
    public OpenIDUsersDto getOpenIDUserById(Long openIdUsersId) {
        OpenIDUsers openIDUsers = openIDUsersRepository.findById(openIdUsersId)
                .orElseThrow(() -> new RuntimeException("Usuario OpenID no encontrado con ID: " + openIdUsersId));
        return openIDUsersMapper.toDto(openIDUsers);
    }

    @Override
    public List<OpenIDUsersDto> getAllOpenIDUsers() {
        List<OpenIDUsers> allOpenIDUsers = openIDUsersRepository.findAll();
        return allOpenIDUsers.stream()
                .map(openIDUsersMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public OpenIDUsersDto createOpenIDUser(OpenIDUsersDto openIDUsersDTO) {
        OpenIDUsers newOpenIDUser = openIDUsersMapper.toEntity(openIDUsersDTO);
        OpenIDUsers savedOpenIDUser = openIDUsersRepository.save(newOpenIDUser);
        return openIDUsersMapper.toDto(savedOpenIDUser);
    }

    @Override
    public OpenIDUsersDto updateOpenIDUser(Long openIdUsersId, OpenIDUsersDto openIDUsersDto) {
        OpenIDUsers existingOpenIDUser = openIDUsersRepository.findById(openIdUsersId)
                .orElseThrow(() -> new RuntimeException("Usuario OpenID no encontrado con ID: " + openIdUsersId));

        if (openIDUsersDto.getSubjectId() != null && !openIDUsersDto.getSubjectId().isEmpty()) {
            existingOpenIDUser.setSubjectId(openIDUsersDto.getSubjectId());
        }
        if (openIDUsersDto.getMail() != null && !openIDUsersDto.getMail().isEmpty()) {
            existingOpenIDUser.setMail(openIDUsersDto.getMail());
        }

        if (openIDUsersDto.getIssuer() != null && !openIDUsersDto.getIssuer().isEmpty()) {
            existingOpenIDUser.setIssuer(openIDUsersDto.getIssuer());
        }

        if (openIDUsersDto.getOpenIdDN() != null && !openIDUsersDto.getOpenIdDN().isEmpty()) {
            existingOpenIDUser.setOpenIdDN(openIDUsersDto.getOpenIdDN());
        }

        OpenIDUsers updatedOpenIDUser = openIDUsersRepository.save(existingOpenIDUser);
        return openIDUsersMapper.toDto(updatedOpenIDUser);
    }

    @Override
    public void deleteOpenIDUser(Long openIdUsersId) {
        openIDUsersRepository.deleteById(openIdUsersId);
    }
}


