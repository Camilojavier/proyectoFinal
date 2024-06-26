package com.camilo.arce.proyecto.web.rest;

import com.camilo.arce.proyecto.dto.OpenIDUsersDto;
import com.camilo.arce.proyecto.services.OpenIDUsersService;
import com.camilo.arce.proyecto.web.api.OpenIDUsersApi;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(OpenIDUsersApi.OPENID_USERS_ROUTE)
public class OpenIDUsersController implements OpenIDUsersApi {

    private final OpenIDUsersService openIDUsersService;

    @Operation(summary = "Get OpenID Users by Id")
    @GetMapping(OPENID_USER_ID)
    public ResponseEntity<OpenIDUsersDto> getOpenIDUserById(@PathVariable Long openidUsersId) {
        Optional<OpenIDUsersDto> openIDUsersDTO = openIDUsersService.getOpenIDUserById(openidUsersId);
        return openIDUsersDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get OpenID Users by User Id")
    @GetMapping(BY_USER_ID)
    public ResponseEntity<?> getOpenIDUserByUserId(@PathVariable Long userId) {
            Optional<OpenIDUsersDto> openIDUsersDto = openIDUsersService.getOpenIDUserByUserId(userId);
            return openIDUsersDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get OpenID Users by Provider Id")
    @GetMapping(BY_PROVIDER_ID)
    public ResponseEntity<OpenIDUsersDto> getOpenIDUserByProviderId(@PathVariable Long providerId) {
        Optional<OpenIDUsersDto> openIDUsersDto = openIDUsersService.getOpenIDUserByProviderId(providerId);
        return openIDUsersDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get All OpenID Users")
    @GetMapping
    public ResponseEntity<List<OpenIDUsersDto>> getAllOpenIDUsers() {
        List<OpenIDUsersDto> openIDUsersDtoList = openIDUsersService.getAllOpenIDUsers();
        return ResponseEntity.ok(openIDUsersDtoList);
    }

    @Operation(summary = "Create OpenID User")
    @PostMapping
    public ResponseEntity<OpenIDUsersDto> createOpenIDUser(@RequestBody OpenIDUsersDto openIDUsersDTO) {
        OpenIDUsersDto createdOpenIDUserDTO = openIDUsersService.createOpenIDUser(openIDUsersDTO);
        return new ResponseEntity<>(createdOpenIDUserDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Update OpenID User")
    @PutMapping(OPENID_USER_ID)
    public ResponseEntity<OpenIDUsersDto> updateOpenIDUser(@PathVariable Long openidUsersId, @RequestBody OpenIDUsersDto openIDUsersDTO) {
        OpenIDUsersDto updatedOpenIDUserDTO = openIDUsersService.updateOpenIDUser(openidUsersId, openIDUsersDTO);
        return ResponseEntity.ok(updatedOpenIDUserDTO);
    }

    @Operation(summary = "Delete OpenID User")
    @DeleteMapping(OPENID_USER_ID)
    public ResponseEntity<Void> deleteOpenIDUser(@PathVariable Long openidUsersId) {
        openIDUsersService.deleteOpenIDUser(openidUsersId);
        return ResponseEntity.noContent().build();
    }
}
