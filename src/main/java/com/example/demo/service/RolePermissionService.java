package com.example.demo.service;

import com.example.demo.entity.RolePermission;

import java.util.List;

public interface RolePermissionService {

    RolePermission grantPermission(RolePermission mapping);

    List<RolePermission> getPermissionsForRole(Long roleId);

    RolePermission getMappingById(Long id);

    void revokePermission(Long mappingId);
}
