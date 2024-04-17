package com.camilo.arce.proyecto.services;

import com.camilo.arce.proyecto.dto.PasswordChangeDto;
import com.camilo.arce.proyecto.dto.UsersDto;

import java.util.List;
import java.util.Optional;

public interface UsersService {

    Optional<UsersDto> getUserById(Long userId);

    Optional<UsersDto> getUserByUsername(String username);


    List<UsersDto> getAllUsers();

    UsersDto createUser(UsersDto usersDTO);

    UsersDto updateUser(Long userId, UsersDto usersDTO);

    UsersDto updatePassword(Long userId, PasswordChangeDto newPassword);

    void deleteUser(Long userId);
}
