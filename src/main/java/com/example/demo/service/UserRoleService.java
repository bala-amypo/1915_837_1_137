package com.example.demo.service;

import com.example.demo.entity.UserRole;

import java.util.List;

public interface UserRoleService {

    UserRole assignRole(UserRole mapping);

    List<UserRole> getRolesForUser(Long userId);

    UserRole getMappingById(Long id);

    void removeRole(Long mappingId);
}
