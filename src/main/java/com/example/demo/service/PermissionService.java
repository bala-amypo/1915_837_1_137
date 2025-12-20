package com.example.demo.service;

import com.example.demo.entity.Permission;

import java.util.List;

public interface PermissionService {

    Permission createPermission(Permission permission);

    List<Permission> getAllPermissions();
}
