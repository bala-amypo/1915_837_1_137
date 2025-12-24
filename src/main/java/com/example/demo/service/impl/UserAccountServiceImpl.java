package com.example.demo.service.impl;

import com.example.demo.entity.UserAccount;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.service.UserAccountService;

import java.util.List;

public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository repo;

    public UserAccountServiceImpl(UserAccountRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserAccount createUser(UserAccount user) {
        if (repo.existsByEmail(user.getEmail())) {
            throw new BadRequestException("Duplicate email");
        }
        return repo.save(user);
    }

    @Override
    public UserAccount updateUser(Long id, UserAccount user) {
        UserAccount existing = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        existing.setEmail(user.getEmail());
        existing.setFullName(user.getFullName());
        return repo.save(existing);
    }

    @Override
    public UserAccount getUserById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public List<UserAccount> getAllUsers() {
        return repo.findAll();
    }

    @Override
    public void deactivateUser(Long id) {
        UserAccount user = getUserById(id);
        user.setActive(false);
        repo.save(user);
    }
}
