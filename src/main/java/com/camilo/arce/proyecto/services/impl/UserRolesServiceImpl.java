package com.camilo.arce.proyecto.services.impl;

import com.camilo.arce.proyecto.domain.entities.Roles;
import com.camilo.arce.proyecto.domain.entities.UserRoles;
import com.camilo.arce.proyecto.domain.entities.Users;
import com.camilo.arce.proyecto.dto.UserRolesDTO;
import com.camilo.arce.proyecto.repositories.RolesRepository;
import com.camilo.arce.proyecto.repositories.UserRolesRepository;
import com.camilo.arce.proyecto.repositories.UsersRepository;
import com.camilo.arce.proyecto.services.UserRolesService;
import com.camilo.arce.proyecto.services.mapper.UserRolesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRolesServiceImpl implements UserRolesService {

    private final UserRolesRepository userRolesRepository;
    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;

    @Autowired
    public UserRolesServiceImpl(UserRolesRepository userRolesRepository, UsersRepository usersRepository, RolesRepository rolesRepository) {
        this.userRolesRepository = userRolesRepository;
        this.usersRepository = usersRepository;
        this.rolesRepository = rolesRepository;
    }

    @Override
    public UserRolesDTO getUserRoleById(Long userRoleId) {
        UserRoles userRole = userRolesRepository.findById(userRoleId)
                .orElseThrow(() -> new RuntimeException("User Role no encontrado con ID: " + userRoleId));
        return UserRolesMapper.INSTANCE.toDto(userRole);
    }

    @Override
    public List<UserRolesDTO> getAllUserRoles() {
        List<UserRoles> allUserRoles = userRolesRepository.findAll();
        return allUserRoles.stream()
                .map(UserRolesMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserRolesDTO createUserRole(UserRolesDTO userRolesDTO) {
        UserRoles newUserRole = UserRolesMapper.INSTANCE.toEntity(userRolesDTO);
        newUserRole.setActive(true);
        UserRoles savedUserRole = userRolesRepository.save(newUserRole);
        return UserRolesMapper.INSTANCE.toDto(savedUserRole);
    }

    @Override
    public UserRolesDTO updateUserRole(Long userRoleId, UserRolesDTO userRolesDTO) {
        UserRoles existingUserRole = userRolesRepository.findById(userRoleId)
                .orElseThrow(() -> new RuntimeException("User Role no encontrado con ID: " + userRoleId));

        if (userRolesDTO.getUsers() != null && userRolesDTO.getUsers().getUserId() != null) {
            Users existingUser = usersRepository.findById(userRolesDTO.getUsers().getUserId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + userRolesDTO.getUsers().getUserId()));

            existingUserRole.setUsers(existingUser);
        }

        if (userRolesDTO.getRoles() != null && userRolesDTO.getRoles().getRoleId() != null) {
            Roles existingRole = rolesRepository.findById(userRolesDTO.getRoles().getRoleId())
                    .orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + userRolesDTO.getRoles().getRoleId()));

            existingUserRole.setRoles(existingRole);
        }
            existingUserRole.setActive(true);

        UserRoles updatedUserRole = userRolesRepository.save(existingUserRole);
        return UserRolesMapper.INSTANCE.toDto(updatedUserRole);
    }


    @Override
    public void deleteUserRole(Long userRoleId) {
        userRolesRepository.deleteById(userRoleId);
    }
    @Override
    public void deactivateUserRole(Long userRoleId) {
        UserRoles existingUserRole = userRolesRepository.findById(userRoleId)
                .orElseThrow(() -> new RuntimeException("User Role no encontrado con ID: " + userRoleId));
        existingUserRole.setActive(false);

        userRolesRepository.save(existingUserRole);
    }
}
