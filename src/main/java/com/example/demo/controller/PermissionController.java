package com.example.demo.controller;

import com.example.demo.entity.Permission;
import com.example.demo.service.PermissionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PostMapping
    public Permission createPermission(@RequestBody Permission permission) {
        return permissionService.createPermission(permission);
    }

    @PutMapping("/{id}")
    public Permission updatePermission(@PathVariable Long id,
                                       @RequestBody Permission permission) {
        return permissionService.updatePermission(id, permission);
    }

    @GetMapping("/{id}")
    public Permission getPermission(@PathVariable Long id) {
        return permissionService.getPermissionById(id);
    }

    @GetMapping
    public List<Permission> getAllPermissions() {
        return permissionService.getAllPermissions();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivatePermission(@PathVariable Long id) {
        permissionService.deactivatePermission(id);
    }
}
