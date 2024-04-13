package com.camilo.arce.proyecto.services.impl;

import com.camilo.arce.proyecto.domain.entities.OpenIDUsers;
import com.camilo.arce.proyecto.dto.OpenIDUsersDto;
import com.camilo.arce.proyecto.repositories.OpenIDUsersRepository;
import com.camilo.arce.proyecto.services.OpenIDUsersService;
import com.camilo.arce.proyecto.services.mapper.OpenIDUsersMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OpenIDUsersServiceImpl implements OpenIDUsersService {

    private final OpenIDUsersRepository openIDUsersRepository;
    private final OpenIDUsersMapper openIDUsersMapper;

    @Override
    public Optional<OpenIDUsersDto> getOpenIDUserById(Long openIdUsersId) {
        return openIDUsersRepository.findById(openIdUsersId).map(openIDUsersMapper::toDto);
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

    @Override
    public boolean isRegisteredDN(String distinguishedName) {
        return openIDUsersRepository.findByOpenIdDN(distinguishedName).isPresent();
    }

    @Override
    public Optional<OpenIDUsersDto> getOpenIDUserByDN(String distinguishedName) {
        return openIDUsersRepository.findByOpenIdDN(distinguishedName).map(openIDUsersMapper::toDto);
    }
}


