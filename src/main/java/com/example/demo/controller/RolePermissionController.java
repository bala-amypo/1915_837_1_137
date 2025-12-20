package com.example.demo.controller;

import com.example.demo.entity.RolePermission;
import com.example.demo.repository.RolePermissionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role-permissions")
public class RolePermissionController {

    private final RolePermissionRepository rolePermissionRepository;

    public RolePermissionController(RolePermissionRepository rolePermissionRepository) {
        this.rolePermissionRepository = rolePermissionRepository;
    }

    @PostMapping
    public RolePermission assignPermission(@RequestBody RolePermission rolePermission) {
        return rolePermissionRepository.save(rolePermission);
    }

    @GetMapping
    public List<RolePermission> getAllRolePermissions() {
        return rolePermissionRepository.findAll();
    }
}
