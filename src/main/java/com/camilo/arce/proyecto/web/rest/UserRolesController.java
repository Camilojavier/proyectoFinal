package com.camilo.arce.proyecto.web.rest;

import com.camilo.arce.proyecto.dto.UserRolesDto;
import com.camilo.arce.proyecto.services.UserRolesService;
import com.camilo.arce.proyecto.web.api.UserRolesApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UserRolesApi.USER_ROLES_ROUTE)
public class UserRolesController {

    private final UserRolesService userRolesService;

    @Autowired
    public UserRolesController(UserRolesService userRolesService) {
        this.userRolesService = userRolesService;
    }

    @GetMapping(UserRolesApi.USER_ROLES_ID)
    public ResponseEntity<UserRolesDto> getUserRoleById(@PathVariable Long userRolesId) {
        UserRolesDto userRoleDTO = userRolesService.getUserRoleById(userRolesId);
        return ResponseEntity.ok(userRoleDTO);
    }

    @GetMapping(UserRolesApi.USERS_BY_ROLE_ID)
    public ResponseEntity<List<UserRolesDto>> getUsersByRoleId(@PathVariable Long roleId) {
        List<UserRolesDto> usersByRoleId = userRolesService.getUsersByRoleId(roleId);
        return ResponseEntity.ok(usersByRoleId);
    }

    @GetMapping(UserRolesApi.ROLES_BY_USER_ID)
    public ResponseEntity<List<UserRolesDto>> getRolesByUserId(@PathVariable Long userId) {
        List<UserRolesDto> rolesByUserId = userRolesService.getRolesByUserId(userId);
        return ResponseEntity.ok(rolesByUserId);
    }

    @GetMapping
    public ResponseEntity<List<UserRolesDto>> getAllUserRoles() {
        List<UserRolesDto> userRolesDtoList = userRolesService.getAllUserRoles();
        return ResponseEntity.ok(userRolesDtoList);
    }

    @PostMapping
    public ResponseEntity<UserRolesDto> createUserRole(@RequestBody UserRolesDto userRolesDTO) {
        UserRolesDto createdUserRoleDTO = userRolesService.createUserRole(userRolesDTO);
        return new ResponseEntity<>(createdUserRoleDTO, HttpStatus.CREATED);
    }

    @PutMapping(UserRolesApi.USER_ROLES_ID)
    public ResponseEntity<UserRolesDto> updateUserRole(@PathVariable Long userRolesId, @RequestBody UserRolesDto userRolesDTO) {
        UserRolesDto updatedUserRoleDTO = userRolesService.updateUserRole(userRolesId, userRolesDTO);
        return ResponseEntity.ok(updatedUserRoleDTO);
    }

    @DeleteMapping(UserRolesApi.USER_ROLES_ID)
    public ResponseEntity<Void> deleteUserRole(@PathVariable Long userRolesId) {
        userRolesService.deleteUserRole(userRolesId);
        return ResponseEntity.noContent().build();
    }
    @RequestMapping(value = UserRolesApi.USER_ROLES_ID, method = RequestMethod.PATCH)
    public ResponseEntity<Void> deactivateUserRole(@PathVariable Long userRolesId) {
        userRolesService.deactivateUserRole(userRolesId);
        return ResponseEntity.noContent().build();
    }
}

