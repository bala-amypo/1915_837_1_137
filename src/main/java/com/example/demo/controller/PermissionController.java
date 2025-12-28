package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.Permission;
import com.example.demo.service.PermissionService;
import io.swagger.v3.oas.annotations.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/permissions") // [cite: 287]
@RequiredArgsConstructor
@Tag(name = "Permission Management") // [cite: 359]
public class PermissionController {

    private final PermissionService permissionService;

    @PostMapping
    public ApiResponse createPermission(@RequestBody Permission permission) {
        return new ApiResponse(true, "Permission created", permissionService.createPermission(permission));
    }

    @PutMapping("/{id}")
    public ApiResponse updatePermission(@PathVariable Long id, @RequestBody Permission permission) {
        return new ApiResponse(true, "Permission updated", permissionService.updatePermission(id, permission));
    }

    @GetMapping("/{id}")
    public ApiResponse getPermissionById(@PathVariable Long id) {
        return new ApiResponse(true, "Permission fetched", permissionService.getPermissionById(id));
    }

    @GetMapping
    public List<Permission> getAllPermissions() {
        return permissionService.getAllPermissions();
    }

    @PutMapping("/{id}/deactivate")
    public ApiResponse deactivatePermission(@PathVariable Long id) {
        permissionService.deactivatePermission(id);
        return new ApiResponse(true, "Permission deactivated");
    }
}