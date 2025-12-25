package com.example.demo.service.impl;

import com.example.demo.entity.UserAccount;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.service.UserAccountService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserAccountServiceImpl implements UserAccountService {
    private final UserAccountRepository repository;

    public UserAccountServiceImpl(UserAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserAccount createUser(UserAccount user) {
        if (repository.existsByEmail(user.getEmail())) {
            throw new BadRequestException("Email already exists");
        }
        return repository.save(user);
    }

    @Override
    public UserAccount updateUser(Long id, UserAccount user) {
        UserAccount existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        existing.setEmail(user.getEmail());
        existing.setFullName(user.getFullName());
        return repository.save(existing);
    }

    @Override
    public UserAccount getUserById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public List<UserAccount> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public void deactivateUser(Long id) {
        UserAccount user = getUserById(id);
        user.setActive(false);
        repository.save(user);
    }
}