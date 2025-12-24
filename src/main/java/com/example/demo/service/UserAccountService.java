package com.example.demo.service;

import com.example.demo.entity.UserAccount;

import java.util.List;

public interface UserAccountService {

    UserAccount createUser(UserAccount user);

    UserAccount updateUser(Long id, UserAccount user);

    UserAccount getUserById(Long id);

    List<UserAccount> getAllUsers();

    void deactivateUser(Long id);
}
