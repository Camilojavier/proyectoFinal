package com.camilo.arce.proyecto.web.rest;

import com.camilo.arce.proyecto.dto.IdTokenDto;
import com.camilo.arce.proyecto.dto.OpenIDUsersDto;
import com.camilo.arce.proyecto.dto.PasswordChangeDto;
import com.camilo.arce.proyecto.dto.UsersDto;
import com.camilo.arce.proyecto.services.OpenIDUsersService;
import com.camilo.arce.proyecto.services.UsersService;
import com.camilo.arce.proyecto.tools.AuthTokenCrypt;
import com.camilo.arce.proyecto.web.api.UsersApi;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
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
    private final OpenIDUsersService openIDUsersService;

    @Operation(summary = "Get User by Id")
    @GetMapping(USER_ID)
    public ResponseEntity<UsersDto> getUserById(@PathVariable Long userId) {
        Optional<UsersDto> usersDTO = usersService.getUserById(userId);
        return usersDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get All Users")
    @GetMapping
    public ResponseEntity<List<UsersDto>> getAllUsers() {
        List<UsersDto> usersDtoList = usersService.getAllUsers();
        return ResponseEntity.ok(usersDtoList);
    }

    @Operation(summary = "Create User")
    @PostMapping
    public ResponseEntity<UsersDto> createUser(@RequestBody UsersDto usersDTO) {
        UsersDto createdUserDTO = usersService.createUser(usersDTO);
        return new ResponseEntity<>(createdUserDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Update User")
    @PutMapping(USER_ID)
    public ResponseEntity<UsersDto> updateUser(@PathVariable Long userId, @RequestBody UsersDto usersDTO) {
        UsersDto updatedUserDTO = usersService.updateUser(userId, usersDTO);
        return ResponseEntity.ok(updatedUserDTO);
    }

    @Operation(summary = "Update User Password")
    @RequestMapping(value = USER_ID, method = RequestMethod.PATCH)
    public ResponseEntity<UsersDto> updatePassword(@PathVariable Long userId, @RequestBody PasswordChangeDto passwordChangeDto) {
        UsersDto updatedUserDTO = usersService.updatePassword(userId, passwordChangeDto);
        return ResponseEntity.ok(updatedUserDTO);
    }

    @Operation(summary = "Delete User")
    @DeleteMapping(USER_ID)
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        usersService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get Authenticated User")
    @GetMapping(AUTH_USER_ROUTE)
    public ResponseEntity<UsersDto> getAuthUser(HttpServletRequest request) throws Exception {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(AUTH_TOKEN)) {
                String token = cookie.getValue();
                IdTokenDto idTokenDTO = AuthTokenCrypt.decryptIdTokenDTO(token);
                if (idTokenDTO.isWithOpenId()) {
                    Optional<OpenIDUsersDto> openIDUsersDto =openIDUsersService.getOpenIDUserByDN(idTokenDTO.getAsDN());
                    if (openIDUsersDto.isPresent()) {
                        return ResponseEntity.ok(openIDUsersDto.get().getUsers());
                    }
                } else {
                    Optional<UsersDto> usersDto = usersService.getUserByUsername(idTokenDTO.getCN());
                    if (usersDto.isPresent()) {
                        return ResponseEntity.ok(usersDto.get());
                    }
                }
            }
        }
        return ResponseEntity.notFound().build();
    }
}

