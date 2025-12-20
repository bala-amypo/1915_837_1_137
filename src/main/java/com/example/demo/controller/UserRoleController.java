package com.example.demo.controller;

import com.example.demo.entity.UserRole;
import com.example.demo.repository.UserRoleRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-roles")
public class UserRoleController {

    private final UserRoleRepository userRoleRepository;

    public UserRoleController(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @PostMapping
    public UserRole assignRole(@RequestBody UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    @GetMapping
    public List<UserRole> getAllUserRoles() {
        return userRoleRepository.findAll();
    }
}
