package com.example.demo.service;

import com.example.demo.entity.Permission;

import java.util.List;

public interface PermissionService {

    Permission createPermission(Permission permission);

    Permission updatePermission(Long id, Permission permission);

    Permission getPermissionById(Long id);

    List<Permission> getAllPermissions();

    void deactivatePermission(Long id);
}
