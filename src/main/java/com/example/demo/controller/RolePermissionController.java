package com.example.demo.controller;

import com.example.demo.entity.RolePermission;
import com.example.demo.service.RolePermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/role-permissions")
@Tag(name = "Role Permission Mapping")
public class RolePermissionController {
    private final RolePermissionService service;

    public RolePermissionController(RolePermissionService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Grant permission to role")
    public ResponseEntity<RolePermission> grantPermission(@RequestBody RolePermission mapping) {
        return ResponseEntity.ok(service.grantPermission(mapping));
    }

    @GetMapping("/role/{roleId}")
    @Operation(summary = "Get permissions for role")
    public ResponseEntity<List<RolePermission>> getPermissionsForRole(@PathVariable Long roleId) {
        return ResponseEntity.ok(service.getPermissionsForRole(roleId));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get mapping by ID")
    public ResponseEntity<RolePermission> getMappingById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getMappingById(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Revoke permission from role")
    public ResponseEntity<Void> revokePermission(@PathVariable Long id) {
        service.revokePermission(id);
        return ResponseEntity.ok().build();
    }
}