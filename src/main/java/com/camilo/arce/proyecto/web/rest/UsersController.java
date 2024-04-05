package com.camilo.arce.proyecto.web.rest;

import com.camilo.arce.proyecto.dto.UsersDto;
import com.camilo.arce.proyecto.services.UsersService;
import com.camilo.arce.proyecto.web.api.UsersApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(UsersApi.USERS_ROUTE)
public class UsersController implements  UsersApi{

    private final UsersService usersService;

    @GetMapping(USER_ID)
    public ResponseEntity<UsersDto> getUserById(@PathVariable Long userId) {
        Optional<UsersDto> usersDTO = usersService.getUserById(userId);
        return usersDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<UsersDto>> getAllUsers() {
        List<UsersDto> usersDtoList = usersService.getAllUsers();
        return ResponseEntity.ok(usersDtoList);
    }

    @PostMapping
    public ResponseEntity<UsersDto> createUser(@RequestBody UsersDto usersDTO) {
        UsersDto createdUserDTO = usersService.createUser(usersDTO);
        return new ResponseEntity<>(createdUserDTO, HttpStatus.CREATED);
    }

    @PutMapping(USER_ID)
    public ResponseEntity<UsersDto> updateUser(@PathVariable Long userId, @RequestBody UsersDto usersDTO) {
        UsersDto updatedUserDTO = usersService.updateUser(userId, usersDTO);
        return ResponseEntity.ok(updatedUserDTO);
    }

    @DeleteMapping(USER_ID)
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        usersService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}

