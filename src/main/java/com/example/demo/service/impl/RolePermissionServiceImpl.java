package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.RolePermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RolePermissionServiceImpl implements RolePermissionService {

    private final RolePermissionRepository rolePermissionRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    @Override
    public RolePermission grantPermission(RolePermission mapping) {
        Role role = roleRepository.findById(mapping.getRole().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));
        Permission permission = permissionRepository.findById(mapping.getPermission().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Permission not found"));

        if (!role.isActive() || !permission.isActive()) {
            throw new BadRequestException("Role or Permission is inactive");
        }

        mapping.setRole(role);
        mapping.setPermission(permission);
        return rolePermissionRepository.save(mapping);
    }

    @Override
    public List<RolePermission> getPermissionsForRole(Long roleId) {
        return rolePermissionRepository.findByRole_Id(roleId);
    }

    @Override
    public RolePermission getMappingById(Long id) {
        return rolePermissionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mapping not found"));
    }

    @Override
    public void revokePermission(Long mappingId) {
        if (!rolePermissionRepository.existsById(mappingId)) {
            throw new ResourceNotFoundException("Mapping not found");
        }
        rolePermissionRepository.deleteById(mappingId);
    }
}