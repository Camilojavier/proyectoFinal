package com.camilo.arce.proyecto.services.impl;

import com.camilo.arce.proyecto.domain.entities.Users;
import com.camilo.arce.proyecto.dto.UsersDTO;
import com.camilo.arce.proyecto.repositories.UsersRepository;
import com.camilo.arce.proyecto.services.UsersService;
import com.camilo.arce.proyecto.services.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UsersDTO getUserById(Long userId) {
        Users users = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + userId));
        return UsersMapper.INSTANCE.toDto(users);
    }

    @Override
    public List<UsersDTO> getAllUsers() {
        List<Users> allUsers = usersRepository.findAll();
        return allUsers.stream()
                .map(UsersMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UsersDTO createUser(UsersDTO usersDTO) {
        Users newUser = UsersMapper.INSTANCE.toEntity(usersDTO);
        Users savedUser = usersRepository.save(newUser);
        return UsersMapper.INSTANCE.toDto(savedUser);
    }

    @Override
    public UsersDTO updateUser(Long userId, UsersDTO usersDTO) {
        Users existingUser = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + userId));

        if (usersDTO.getUsername() != null && !usersDTO.getUsername().isEmpty()) {
            existingUser.setUsername(usersDTO.getUsername());
        }

        if (usersDTO.getFirstName() != null && !usersDTO.getFirstName().isEmpty()) {
            existingUser.setFirstName(usersDTO.getFirstName());
        }

        if (usersDTO.getLastName() != null && !usersDTO.getLastName().isEmpty()) {
            existingUser.setLastName(usersDTO.getLastName());
        }

        if (usersDTO.getEmail() != null && !usersDTO.getEmail().isEmpty()) {
            existingUser.setEmail(usersDTO.getEmail());
        }

        if (usersDTO.getHashedPassword() != null && !usersDTO.getHashedPassword().isEmpty()) {
            existingUser.setHashedPassword(usersDTO.getHashedPassword());
        }

        if (usersDTO.getPhone() != null && !usersDTO.getPhone().isEmpty()) {
            existingUser.setPhone(usersDTO.getPhone());
        }

        Users updatedUser = usersRepository.save(existingUser);
        return UsersMapper.INSTANCE.toDto(updatedUser);
    }


    @Override
    public void deleteUser(Long userId) {
        usersRepository.deleteById(userId);
    }
}
