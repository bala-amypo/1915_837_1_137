package com.example.demo.controller;

import com.example.demo.entity.UserRole;
import com.example.demo.service.UserRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/user-roles")
@Tag(name = "User Role Assignment")
public class UserRoleController {
    private final UserRoleService service;

    public UserRoleController(UserRoleService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Assign role to user")
    public ResponseEntity<UserRole> assignRole(@RequestBody UserRole mapping) {
        return ResponseEntity.ok(service.assignRole(mapping));
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get roles for user")
    public ResponseEntity<List<UserRole>> getRolesForUser(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getRolesForUser(userId));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get mapping by ID")
    public ResponseEntity<UserRole> getMappingById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getMappingById(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove role from user")
    public ResponseEntity<Void> removeRole(@PathVariable Long id) {
        service.removeRole(id);
        return ResponseEntity.ok().build();
    }
}