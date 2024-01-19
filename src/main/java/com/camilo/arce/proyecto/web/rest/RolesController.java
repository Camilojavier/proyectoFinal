package com.camilo.arce.proyecto.web.rest;

import com.camilo.arce.proyecto.dto.RolesDto;
import com.camilo.arce.proyecto.services.RolesService;
import com.camilo.arce.proyecto.web.api.Api;
import com.camilo.arce.proyecto.web.api.RolesApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(RolesApi.ROLES_ROUTE)
public class RolesController {

    private final RolesService rolesService;

    @Autowired
    public RolesController(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @GetMapping(Api.ROLE_ID)
    public ResponseEntity<RolesDto> getRoleById(@PathVariable Long roleId) {
        Optional<RolesDto> roleDTO = rolesService.getRoleById(roleId);
        return roleDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping
    public ResponseEntity<List<RolesDto>> getAllRoles() {
        List<RolesDto> rolesDtoList = rolesService.getAllRoles();
        return ResponseEntity.ok(rolesDtoList);
    }

    @PostMapping
    public ResponseEntity<RolesDto> createRole(@RequestBody RolesDto rolesDTO) {
        RolesDto createdRoleDTO = rolesService.createRole(rolesDTO);
        return new ResponseEntity<>(createdRoleDTO, HttpStatus.CREATED);
    }

    @PutMapping(Api.ROLE_ID)
    public ResponseEntity<RolesDto> updateRole(@PathVariable Long roleId, @RequestBody RolesDto rolesDTO) {
        RolesDto updatedRoleDTO = rolesService.updateRole(roleId, rolesDTO);
        return ResponseEntity.ok(updatedRoleDTO);
    }

    @DeleteMapping(Api.ROLE_ID)
    public ResponseEntity<Void> deleteRole(@PathVariable Long roleId) {
        rolesService.deleteRole(roleId);
        return ResponseEntity.noContent().build();
    }
}
