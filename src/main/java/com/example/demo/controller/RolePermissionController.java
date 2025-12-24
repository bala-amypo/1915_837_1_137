package com.example.demo.controller;

import com.example.demo.entity.RolePermission;
import com.example.demo.service.RolePermissionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role-permissions")
public class RolePermissionController {

    private final RolePermissionService rolePermissionService;

    public RolePermissionController(RolePermissionService rolePermissionService) {
        this.rolePermissionService = rolePermissionService;
    }

    @PostMapping
    public RolePermission grantPermission(@RequestBody RolePermission mapping) {
        return rolePermissionService.grantPermission(mapping);
    }

    @GetMapping("/role/{roleId}")
    public List<RolePermission> getPermissionsForRole(@PathVariable Long roleId) {
        return rolePermissionService.getPermissionsForRole(roleId);
    }

    @GetMapping("/{id}")
    public RolePermission getMapping(@PathVariable Long id) {
        return rolePermissionService.getMappingById(id);
    }

    @DeleteMapping("/{id}")
    public void revokePermission(@PathVariable Long id) {
        rolePermissionService.revokePermission(id);
    }
}
