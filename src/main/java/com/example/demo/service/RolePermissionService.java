package com.example.demo.service;

import com.example.demo.entity.RolePermission;

import java.util.List;

public interface RolePermissionService {

    RolePermission assignPermission(RolePermission rolePermission);

    List<RolePermission> getAllRolePermissions();
}
