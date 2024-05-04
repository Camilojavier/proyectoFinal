package com.camilo.arce.proyecto.web.rest;

import com.camilo.arce.proyecto.dto.RolesDto;
import com.camilo.arce.proyecto.services.RolesService;
import com.camilo.arce.proyecto.web.api.RolesApi;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(RolesApi.ROLES_ROUTE)
public class RolesController implements RolesApi{

    private final RolesService rolesService;

    @Operation(summary = "Get Role by Id")
    @GetMapping(ROLE_ID)
    public ResponseEntity<RolesDto> getRoleById(@PathVariable Long roleId) {
        Optional<RolesDto> roleDTO = rolesService.getRoleById(roleId);
        return roleDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get All Roles")
    @GetMapping
    public ResponseEntity<List<RolesDto>> getAllRoles() {
        List<RolesDto> rolesDtoList = rolesService.getAllRoles();
        return ResponseEntity.ok(rolesDtoList);
    }

    @Operation(summary = "Create Role")
    @PostMapping
    public ResponseEntity<RolesDto> createRole(@RequestBody RolesDto rolesDTO) {
        RolesDto createdRoleDTO = rolesService.createRole(rolesDTO);
        return new ResponseEntity<>(createdRoleDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Update Role")
    @PutMapping(ROLE_ID)
    public ResponseEntity<RolesDto> updateRole(@PathVariable Long roleId, @RequestBody RolesDto rolesDTO) {
        RolesDto updatedRoleDTO = rolesService.updateRole(roleId, rolesDTO);
        return ResponseEntity.ok(updatedRoleDTO);
    }

    @Operation(summary = "Delete Role")
    @DeleteMapping(ROLE_ID)
    public ResponseEntity<Void> deleteRole(@PathVariable Long roleId) {
        rolesService.deleteRole(roleId);
        return ResponseEntity.noContent().build();
    }
}
