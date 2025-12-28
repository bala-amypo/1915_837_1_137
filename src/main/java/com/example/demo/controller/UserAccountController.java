package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.UserAccount;
import com.example.demo.service.UserAccountService;
import io.swagger.v3.oas.annotations.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users") // [cite: 277]
@RequiredArgsConstructor
@Tag(name = "User Account Management") // [cite: 357]
public class UserAccountController {

    private final UserAccountService userService;

    @PostMapping
    public ApiResponse createUser(@RequestBody UserAccount user) {
        return new ApiResponse(true, "User created", userService.createUser(user));
    }

    @PutMapping("/{id}")
    public ApiResponse updateUser(@PathVariable Long id, @RequestBody UserAccount user) {
        return new ApiResponse(true, "User updated", userService.updateUser(id, user));
    }

    @GetMapping("/{id}")
    public ApiResponse getUserById(@PathVariable Long id) {
        return new ApiResponse(true, "User fetched", userService.getUserById(id));
    }

    @GetMapping
    public List<UserAccount> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/{id}/deactivate")
    public ApiResponse deactivateUser(@PathVariable Long id) {
        userService.deactivateUser(id);
        return new ApiResponse(true, "User deactivated");
    }
}