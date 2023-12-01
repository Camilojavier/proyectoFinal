package com.camilo.arce.proyecto.services;

import com.camilo.arce.proyecto.dto.UsersDTO;

import java.util.List;

public interface UsersService {

    UsersDTO getUserById(Long userId);

    List<UsersDTO> getAllUsers();

    UsersDTO createUser(UsersDTO usersDTO);

    UsersDTO updateUser(Long userId, UsersDTO usersDTO);

    void deleteUser(Long userId);
}
