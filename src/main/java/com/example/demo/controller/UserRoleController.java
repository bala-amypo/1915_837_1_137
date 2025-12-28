package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.UserRole;
import com.example.demo.service.UserRoleService;
import io.swagger.v3.oas.annotations.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/user-roles") // [cite: 297]
@RequiredArgsConstructor
@Tag(name = "User Role Assignment") // [cite: 361]
public class UserRoleController {

    private final UserRoleService userRoleService;

    @PostMapping
    public ApiResponse assignRole(@RequestBody UserRole mapping) {
        return new ApiResponse(true, "Role assigned to User", userRoleService.assignRole(mapping));
    }

    @GetMapping("/user/{userId}")
    public List<UserRole> getRolesForUser(@PathVariable Long userId) {
        return userRoleService.getRolesForUser(userId);
    }

    @GetMapping("/{id}")
    public ApiResponse getMappingById(@PathVariable Long id) {
        return new ApiResponse(true, "Mapping fetched", userRoleService.getMappingById(id));
    }

    @DeleteMapping("/{id}")
    public ApiResponse removeRole(@PathVariable Long id) {
        userRoleService.removeRole(id);
        return new ApiResponse(true, "Role removed from User");
    }
}