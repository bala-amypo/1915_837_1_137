package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.Role;
import com.example.demo.service.RoleService;
import io.swagger.v3.oas.annotations.tags.Tag; // Fixed Import
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
@Tag(name = "Role Management")
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    public ApiResponse createRole(@RequestBody Role role) {
        return new ApiResponse(true, "Role created", roleService.createRole(role));
    }

    @PutMapping("/{id}")
    public ApiResponse updateRole(@PathVariable Long id, @RequestBody Role role) {
        return new ApiResponse(true, "Role updated", roleService.updateRole(id, role));
    }

    @GetMapping("/{id}")
    public ApiResponse getRoleById(@PathVariable Long id) {
        return new ApiResponse(true, "Role fetched", roleService.getRoleById(id));
    }

    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @PutMapping("/{id}/deactivate")
    public ApiResponse deactivateRole(@PathVariable Long id) {
        roleService.deactivateRole(id);
        return new ApiResponse(true, "Role deactivated");
    }
}