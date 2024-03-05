package com.camilo.arce.proyecto.services.impl;

import com.camilo.arce.proyecto.domain.entities.Roles;
import com.camilo.arce.proyecto.dto.RolesDto;
import com.camilo.arce.proyecto.repositories.RolesRepository;
import com.camilo.arce.proyecto.services.RolesService;
import com.camilo.arce.proyecto.services.mapper.RolesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RolesServiceImpl implements RolesService {

    private final RolesRepository rolesRepository;
    private final RolesMapper rolesMapper;

    @Override
    @Transactional(readOnly = true)
    public Optional<RolesDto> getRoleById(Long roleId) {
        return rolesRepository.findById(roleId).map(rolesMapper::toDto);
    }

    @Override
    public List<RolesDto> getAllRoles() {
        return rolesRepository.findAll()
                .stream()
                .map(rolesMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RolesDto createRole(RolesDto rolesDto) {
        return rolesMapper.toDto(rolesRepository.save(rolesMapper.toEntity(rolesDto)));
    }

    @Override
    public RolesDto updateRole(Long roleId, RolesDto rolesDTO) {
        Roles existingRole = rolesRepository.findById(roleId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Rol no encontrado con ID: " + roleId));

        if (rolesDTO.getName() != null && !rolesDTO.getName().isEmpty()) {
            existingRole.setName(rolesDTO.getName());
        }

        Roles updatedRole = rolesRepository.save(existingRole);
        return rolesMapper.toDto(updatedRole);
    }

    @Override
    public void deleteRole(Long roleId) {
        rolesRepository.deleteById(roleId);
    }
}

