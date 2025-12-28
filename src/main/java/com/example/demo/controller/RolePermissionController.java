package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.RolePermission;
import com.example.demo.service.RolePermissionService;
import io.swagger.v3.oas.annotations.tags.Tag; // Fixed Import
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/role-permissions")
@RequiredArgsConstructor
@Tag(name = "Role Permission Mapping")
public class RolePermissionController {

    private final RolePermissionService rolePermissionService;

    @PostMapping
    public ApiResponse grantPermission(@RequestBody RolePermission mapping) {
        return new ApiResponse(true, "Permission granted to Role", rolePermissionService.grantPermission(mapping));
    }

    @GetMapping("/role/{roleId}")
    public List<RolePermission> getPermissionsForRole(@PathVariable Long roleId) {
        return rolePermissionService.getPermissionsForRole(roleId);
    }

    @GetMapping("/{id}")
    public ApiResponse getMappingById(@PathVariable Long id) {
        return new ApiResponse(true, "Mapping fetched", rolePermissionService.getMappingById(id));
    }

    @DeleteMapping("/{id}")
    public ApiResponse revokePermission(@PathVariable Long id) {
        rolePermissionService.revokePermission(id);
        return new ApiResponse(true, "Permission revoked from Role");
    }
}