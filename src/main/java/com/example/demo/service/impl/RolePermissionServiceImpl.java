package com.example.demo.service.impl;

import com.example.demo.entity.Permission;
import com.example.demo.entity.RolePermission;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.PermissionRepository;
import com.example.demo.repository.RolePermissionRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.RolePermissionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    private final RolePermissionRepository rolePermissionRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public RolePermissionServiceImpl(RolePermissionRepository rolePermissionRepository,
                                     RoleRepository roleRepository,
                                     PermissionRepository permissionRepository) {
        this.rolePermissionRepository = rolePermissionRepository;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    // ===============================
    // GRANT PERMISSION
    // ===============================
    @Override
    public RolePermission grantPermission(RolePermission mapping) {

        Long roleId = mapping.getRole().getId();
        Long permissionId = mapping.getPermission().getId();

        permissionRepository.findById(permissionId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Permission not found"));

        roleRepository.findById(roleId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Role not found"));

        mapping.setGrantedAt(LocalDateTime.now());
        return rolePermissionRepository.save(mapping);
    }

    // ===============================
    // GET PERMISSIONS FOR ROLE  ✅ CHANGED METHOD
    // ===============================
    @Override
    public List<RolePermission> getPermissionsForRole(Long roleId) {
        return rolePermissionRepository.findByRole_Id(roleId);
    }

    // ===============================
    // GET MAPPING BY ID
    // ===============================
    @Override
    public RolePermission getMappingById(Long id) {
        return rolePermissionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Role-Permission mapping not found"));
    }

    // ===============================
    // REVOKE PERMISSION
    // ===============================
    @Override
    public void revokePermission(Long mappingId) {

        RolePermission mapping = rolePermissionRepository.findById(mappingId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Role-Permission mapping not found"));

        rolePermissionRepository.delete(mapping);
    }
}
