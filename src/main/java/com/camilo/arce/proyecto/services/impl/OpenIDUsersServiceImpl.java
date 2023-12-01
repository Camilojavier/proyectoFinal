package com.camilo.arce.proyecto.services.impl;

import com.camilo.arce.proyecto.domain.entities.OpenIDUsers;
import com.camilo.arce.proyecto.dto.OpenIDUsersDTO;
import com.camilo.arce.proyecto.repositories.OpenIDUsersRepository;
import com.camilo.arce.proyecto.repositories.ProvidersRepository;
import com.camilo.arce.proyecto.repositories.UsersRepository;
import com.camilo.arce.proyecto.services.OpenIDUsersService;
import com.camilo.arce.proyecto.services.mapper.OpenIDUsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OpenIDUsersServiceImpl implements OpenIDUsersService {

    private final OpenIDUsersRepository openIDUsersRepository;
    private final UsersRepository usersRepository;
    private final ProvidersRepository providersRepository;

    @Autowired
    public OpenIDUsersServiceImpl(OpenIDUsersRepository openIDUsersRepository, UsersRepository usersRepository, ProvidersRepository providersRepository) {
        this.openIDUsersRepository = openIDUsersRepository;
        this.usersRepository = usersRepository;
        this.providersRepository = providersRepository;
    }

    @Override
    public OpenIDUsersDTO getOpenIDUserById(Long openIdUsersId) {
        OpenIDUsers openIDUsers = openIDUsersRepository.findById(openIdUsersId)
                .orElseThrow(() -> new RuntimeException("Usuario OpenID no encontrado con ID: " + openIdUsersId));
        return OpenIDUsersMapper.INSTANCE.toDto(openIDUsers);
    }

    @Override
    public List<OpenIDUsersDTO> getAllOpenIDUsers() {
        List<OpenIDUsers> allOpenIDUsers = openIDUsersRepository.findAll();
        return allOpenIDUsers.stream()
                .map(OpenIDUsersMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public OpenIDUsersDTO createOpenIDUser(OpenIDUsersDTO openIDUsersDTO) {
        OpenIDUsers newOpenIDUser = OpenIDUsersMapper.INSTANCE.toEntity(openIDUsersDTO);
        OpenIDUsers savedOpenIDUser = openIDUsersRepository.save(newOpenIDUser);
        return OpenIDUsersMapper.INSTANCE.toDto(savedOpenIDUser);
    }

    @Override
    public OpenIDUsersDTO updateOpenIDUser(Long openIdUsersId, OpenIDUsersDTO openIDUsersDTO) {
        OpenIDUsers existingOpenIDUser = openIDUsersRepository.findById(openIdUsersId)
                .orElseThrow(() -> new RuntimeException("Usuario OpenID no encontrado con ID: " + openIdUsersId));

        if (openIDUsersDTO.getSubjectId() != null && !openIDUsersDTO.getSubjectId().isEmpty()) {
            existingOpenIDUser.setSubjectId(openIDUsersDTO.getSubjectId());
        }

        if (openIDUsersDTO.getMail() != null && !openIDUsersDTO.getMail().isEmpty()) {
            existingOpenIDUser.setMail(openIDUsersDTO.getMail());
        }

        if (openIDUsersDTO.getIssuer() != null && !openIDUsersDTO.getIssuer().isEmpty()) {
            existingOpenIDUser.setIssuer(openIDUsersDTO.getIssuer());
        }

        if (openIDUsersDTO.getOpenIdDN() != null && !openIDUsersDTO.getOpenIdDN().isEmpty()) {
            existingOpenIDUser.setOpenIdDN(openIDUsersDTO.getOpenIdDN());
        }
        OpenIDUsers updatedOpenIDUser = openIDUsersRepository.save(existingOpenIDUser);
        return OpenIDUsersMapper.INSTANCE.toDto(updatedOpenIDUser);
    }

    @Override
    public void deleteOpenIDUser(Long openIdUsersId) {
        openIDUsersRepository.deleteById(openIdUsersId);
    }
}
