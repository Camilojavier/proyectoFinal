package com.camilo.arce.proyecto.services;

import com.camilo.arce.proyecto.dto.UsersDto;

import java.util.List;
import java.util.Optional;

public interface UsersService {

    Optional<UsersDto> getUserById(Long userId);

    List<UsersDto> getAllUsers();

    UsersDto createUser(UsersDto usersDTO);

    UsersDto updateUser(Long userId, UsersDto usersDTO);

    void deleteUser(Long userId);
}
