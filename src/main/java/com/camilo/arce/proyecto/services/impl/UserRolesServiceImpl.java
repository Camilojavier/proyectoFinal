package com.camilo.arce.proyecto.services.impl;

import com.camilo.arce.proyecto.domain.entities.Roles;
import com.camilo.arce.proyecto.domain.entities.UserRoles;
import com.camilo.arce.proyecto.domain.entities.Users;
import com.camilo.arce.proyecto.dto.UserRolesDto;
import com.camilo.arce.proyecto.repositories.RolesRepository;
import com.camilo.arce.proyecto.repositories.UserRolesRepository;
import com.camilo.arce.proyecto.repositories.UsersRepository;
import com.camilo.arce.proyecto.services.UserRolesService;
import com.camilo.arce.proyecto.services.mapper.UserRolesMapper;
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
public class UserRolesServiceImpl implements UserRolesService {

    private final UserRolesRepository userRolesRepository;
    private final UserRolesMapper userRolesMapper;
    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;

    @Override
    public Optional<UserRolesDto> getUserRoleById(Long userRoleId) {
        return userRolesRepository.findById(userRoleId)
                .map(userRolesMapper::toDto);
    }

    @Override
    public List<UserRolesDto> getAllUserRoles() {
        return userRolesRepository.findAll()
                .stream()
                .map(userRolesMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserRolesDto createUserRole(UserRolesDto userRolesDTO) {
        UserRoles userRoles = userRolesRepository.save(userRolesMapper.toEntity(userRolesDTO));
        return userRolesMapper.toDto(userRoles);
    }

    @Override
    public UserRolesDto updateUserRole(Long userRoleId, UserRolesDto userRolesDTO) {
        UserRoles existingUserRole = userRolesRepository.findById(userRoleId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "UserRole no encontrado con ID: " + userRoleId));

        if (userRolesDTO.getRoles() != null) {
            Optional<Roles> updatedRole = rolesRepository.findById(userRolesDTO.getRoles().getRoleId());
            if (updatedRole.isPresent()) {
                existingUserRole.setRoles(updatedRole.get());
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Rol no encontrado con ID: " + userRolesDTO.getUsers().getUserId());
            }
        }

        if (userRolesDTO.getUsers() != null) {
            Optional<Users> updatedUser = usersRepository.findById(userRolesDTO.getUsers().getUserId());
            if (updatedUser.isPresent()) {
                existingUserRole.setUsers(updatedUser.get());
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado con ID: " + userRolesDTO.getUsers().getUserId());
            }
        }

        UserRoles updatedUserRole = userRolesRepository.save(existingUserRole);
        return userRolesMapper.toDto(updatedUserRole);
    }

    @Override
    public void deleteUserRole(Long userRoleId) {
        userRolesRepository.deleteById(userRoleId);
    }

    @Override
    public void deactivateUserRole(Long userRoleId) {
        UserRoles userRoles = userRolesRepository.findById(userRoleId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "UserRole no encontrado con ID: " + userRoleId));

        userRoles.setActive(false);
        userRolesRepository.save(userRoles);
    }

    @Override
    public List<UserRolesDto> getUsersByRoleId(Long roleId) {

        return userRolesRepository.findByRoles_RoleIdAndActiveIsTrue(roleId).stream()
                .map(userRolesMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserRolesDto> getRolesByUserId(Long userId) {
        return userRolesRepository.findByUsers_UserIdAndActiveIsTrue(userId).stream()
                .map(userRolesMapper::toDto)
                .collect(Collectors.toList());
    }

}

