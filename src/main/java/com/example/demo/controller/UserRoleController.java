package com.example.demo.controller;

import com.example.demo.entity.UserRole;
import com.example.demo.service.UserRoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-roles")
public class UserRoleController {

    private final UserRoleService userRoleService;

    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @PostMapping
    public UserRole assignRole(@RequestBody UserRole mapping) {
        return userRoleService.assignRole(mapping);
    }

    @GetMapping("/user/{userId}")
    public List<UserRole> getRolesForUser(@PathVariable Long userId) {
        return userRoleService.getRolesForUser(userId);
    }

    @GetMapping("/{id}")
    public UserRole getMapping(@PathVariable Long id) {
        return userRoleService.getMappingById(id);
    }

    @DeleteMapping("/{id}")
    public void removeRole(@PathVariable Long id) {
        userRoleService.removeRole(id);
    }
}
