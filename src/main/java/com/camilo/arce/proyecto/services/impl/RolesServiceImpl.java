package com.camilo.arce.proyecto.services.impl;

import com.camilo.arce.proyecto.domain.entities.Roles;
import com.camilo.arce.proyecto.dto.RolesDTO;
import com.camilo.arce.proyecto.repositories.RolesRepository;
import com.camilo.arce.proyecto.services.RolesService;
import com.camilo.arce.proyecto.services.mapper.RolesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RolesServiceImpl implements RolesService {

    private final RolesRepository rolesRepository;

    @Autowired
    public RolesServiceImpl(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    @Override
    public RolesDTO getRoleById(Long roleId) {
        Roles role = rolesRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + roleId));
        return RolesMapper.INSTANCE.toDto(role);
    }

    @Override
    public List<RolesDTO> getAllRoles() {
        List<Roles> allRoles = rolesRepository.findAll();
        return allRoles.stream()
                .map(RolesMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RolesDTO createRole(RolesDTO rolesDTO) {
        Roles newRole = RolesMapper.INSTANCE.toEntity(rolesDTO);
        Roles savedRole = rolesRepository.save(newRole);
        return RolesMapper.INSTANCE.toDto(savedRole);
    }

    @Override
    public RolesDTO updateRole(Long roleId, RolesDTO rolesDTO) {
        Roles existingRole = rolesRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + roleId));
        if (rolesDTO.getName() != null && !rolesDTO.getName().isEmpty()) {
            existingRole.setName(rolesDTO.getName());
        }
        Roles updatedRole = rolesRepository.save(existingRole);
        return RolesMapper.INSTANCE.toDto(updatedRole);
    }

    @Override
    public void deleteRole(Long roleId) {
        rolesRepository.deleteById(roleId);
    }
}
