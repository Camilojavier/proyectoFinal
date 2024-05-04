package com.camilo.arce.proyecto.web.rest;

import com.camilo.arce.proyecto.dto.UserRolesDto;
import com.camilo.arce.proyecto.services.UserRolesService;
import com.camilo.arce.proyecto.web.api.UserRolesApi;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(UserRolesApi.USER_ROLES_ROUTE)
public class UserRolesController implements  UserRolesApi {

    private final UserRolesService userRolesService;

    @Operation(summary = "Get User-Role by Id")
    @GetMapping(USER_ROLES_ID)
    public ResponseEntity<UserRolesDto> getUserRoleById(@PathVariable Long userRolesId) {
        Optional<UserRolesDto> userRoleDTO = userRolesService.getUserRoleById(userRolesId);
        return userRoleDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get Users by Role Id")
    @GetMapping(BY_ROLE_ID)
    public ResponseEntity<List<UserRolesDto>> getUsersByRoleId(@PathVariable Long roleId) {
        List<UserRolesDto> usersByRoleId = userRolesService.getUsersByRoleId(roleId);
        return ResponseEntity.ok(usersByRoleId);
    }

    @Operation(summary = "Get Roles by User Id")
    @GetMapping(BY_USER_ID)
    public ResponseEntity<List<UserRolesDto>> getRolesByUserId(@PathVariable Long userId) {
        List<UserRolesDto> rolesByUserId = userRolesService.getRolesByUserId(userId);
        return ResponseEntity.ok(rolesByUserId);
    }

    @Operation(summary = "Get All Users-Roles")
    @GetMapping
    public ResponseEntity<List<UserRolesDto>> getAllUserRoles() {
        List<UserRolesDto> userRolesDtoList = userRolesService.getAllUserRoles();
        return ResponseEntity.ok(userRolesDtoList);
    }

    @Operation(summary = "Create User-Role")
    @PostMapping
    public ResponseEntity<UserRolesDto> createUserRole(@RequestBody UserRolesDto userRolesDTO) {
        UserRolesDto createdUserRoleDTO = userRolesService.createUserRole(userRolesDTO);
        return new ResponseEntity<>(createdUserRoleDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Update User-Role")
    @PutMapping(USER_ROLES_ID)
    public ResponseEntity<UserRolesDto> updateUserRole(@PathVariable Long userRolesId, @RequestBody UserRolesDto userRolesDTO) {
        UserRolesDto updatedUserRoleDTO = userRolesService.updateUserRole(userRolesId, userRolesDTO);
        return ResponseEntity.ok(updatedUserRoleDTO);
    }

    @Operation(summary = "Delete User-Role")
    @DeleteMapping(USER_ROLES_ID)
    public ResponseEntity<Void> deleteUserRole(@PathVariable Long userRolesId) {
        userRolesService.deleteUserRole(userRolesId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Deactivate User-Role")
    @RequestMapping(value = USER_ROLES_ID, method = RequestMethod.PATCH)
    public ResponseEntity<Void> deactivateUserRole(@PathVariable Long userRolesId) {
        userRolesService.deactivateUserRole(userRolesId);
        return ResponseEntity.noContent().build();
    }
}

