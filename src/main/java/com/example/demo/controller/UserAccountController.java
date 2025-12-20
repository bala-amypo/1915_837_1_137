package com.example.demo.controller;

import com.example.demo.entity.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserAccountController {

    private final UserAccountRepository userAccountRepository;

    public UserAccountController(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @PostMapping
    public UserAccount createUser(@RequestBody UserAccount user) {
        return userAccountRepository.save(user);
    }

    @GetMapping
    public List<UserAccount> getAllUsers() {
        return userAccountRepository.findAll();
    }
}
