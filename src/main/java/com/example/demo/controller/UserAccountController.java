package com.example.demo.controller;

import com.example.demo.entity.UserAccount;
import com.example.demo.service.UserAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserAccountController {

    private final UserAccountService userService;

    public UserAccountController(UserAccountService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserAccount createUser(@RequestBody UserAccount user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public UserAccount updateUser(@PathVariable Long id,
                                  @RequestBody UserAccount user) {
        return userService.updateUser(id, user);
    }

    @GetMapping("/{id}")
    public UserAccount getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping
    public List<UserAccount> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivateUser(@PathVariable Long id) {
        userService.deactivateUser(id);
    }
}
