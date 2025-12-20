package com.example.demo.controller;

import com.example.demo.entity.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserAccountRepository userAccountRepository;

    public AuthController(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @PostMapping("/register")
    public UserAccount register(@RequestBody UserAccount user) {
        if (userAccountRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        return userAccountRepository.save(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserAccount request) {

        Optional<UserAccount> userOpt =
                userAccountRepository.findByEmail(request.getEmail());

        if (userOpt.isEmpty()) {
            return "User not found";
        }

        if (!userOpt.get().getPassword().equals(request.getPassword())) {
            return "Invalid password";
        }

        return "Login successful";
    }
}
