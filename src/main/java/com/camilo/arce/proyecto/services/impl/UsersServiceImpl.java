package com.camilo.arce.proyecto.services.impl;

import com.camilo.arce.proyecto.domain.entities.Users;
import com.camilo.arce.proyecto.dto.PasswordChangeDto;
import com.camilo.arce.proyecto.dto.UsersDto;
import com.camilo.arce.proyecto.repositories.UsersRepository;
import com.camilo.arce.proyecto.services.UsersService;
import com.camilo.arce.proyecto.services.mapper.UsersMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;
    private final BCryptPasswordEncoder passwordEncoder;


    @Override
    @Transactional(readOnly = true)
    public Optional<UsersDto> getUserById(Long userId) {
        return usersRepository.findById(userId).map(usersMapper::toDto);
    }

    @Override
    public Optional<UsersDto> getUserByUsername(String username) {
        return usersRepository.findByUsername(username).map(usersMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsersDto> getAllUsers() {
        return usersRepository.findAll()
                .stream()
                .map(usersMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UsersDto createUser(UsersDto usersDto) {
        String encryptedPassword = passwordEncoder.encode(usersDto.getHashedPassword());
        usersDto.setHashedPassword(encryptedPassword);
        Users savedUser = usersRepository.save(usersMapper.toEntity(usersDto));
        return usersMapper.toDto(savedUser);
    }

    @Override
    public UsersDto updateUser(Long userId, UsersDto usersDto) {
        Users existingUser = usersRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuario no encontrado con ID: " + userId));

        if (usersDto.getUsername() != null && !usersDto.getUsername().isEmpty()) {
            existingUser.setUsername(usersDto.getUsername());
        }

        if (usersDto.getFirstName() != null && !usersDto.getFirstName().isEmpty()) {
            existingUser.setFirstName(usersDto.getFirstName());
        }

        if (usersDto.getLastName() != null && !usersDto.getLastName().isEmpty()) {
            existingUser.setLastName(usersDto.getLastName());
        }

        if (usersDto.getEmail() != null && !usersDto.getEmail().isEmpty()) {
            existingUser.setEmail(usersDto.getEmail());
        }

        if (usersDto.getPhone() != null && !usersDto.getPhone().isEmpty()) {
            existingUser.setPhone(usersDto.getPhone());
        }

        Users updatedUser = usersRepository.save(existingUser);
        return usersMapper.toDto(updatedUser);
    }

    @Override
    public UsersDto updatePassword(Long userId, PasswordChangeDto passwordChangeDto) {
        Users existingUser = usersRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuario no encontrado con ID: " + userId));
        final String oldPassword = passwordChangeDto.getOldPassword();
        final String newPassword = passwordChangeDto.getNewPassword();
        if (passwordEncoder.matches(oldPassword, existingUser.getHashedPassword())) {
            existingUser.setHashedPassword(passwordEncoder.encode(newPassword));
        }
        Users updatedUser = usersRepository.save(existingUser);
        return usersMapper.toDto(updatedUser);
    }


    @Override
    public void deleteUser(Long userId) {
        usersRepository.deleteById(userId);
    }
}
