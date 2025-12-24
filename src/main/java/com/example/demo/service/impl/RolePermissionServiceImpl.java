package com.example.demo.service.impl;

import com.example.demo.entity.Permission;
import com.example.demo.entity.Role;
import com.example.demo.entity.RolePermission;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.PermissionRepository;
import com.example.demo.repository.RolePermissionRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.RolePermissionService;

import java.util.List;

public class RolePermissionServiceImpl implements RolePermissionService {

    private final RolePermissionRepository rpRepo;
    private final RoleRepository roleRepo;
    private final PermissionRepository permRepo;

    public RolePermissionServiceImpl(RolePermissionRepository rpRepo,
                                     RoleRepository roleRepo,
                                     PermissionRepository permRepo) {
        this.rpRepo = rpRepo;
        this.roleRepo = roleRepo;
        this.permRepo = permRepo;
    }

    @Override
    public RolePermission grantPermission(RolePermission mapping) {
        Role role = roleRepo.findById(mapping.getRole().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        Permission permission = permRepo.findById(mapping.getPermission().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Permission not found"));

        if (!role.isActive() || !permission.isActive()) {
            throw new BadRequestException("Inactive role or permission");
        }

        return rpRepo.save(mapping);
    }

    @Override
    public List<RolePermission> getPermissionsForRole(Long roleId) {
        return rpRepo.findByRole_Id(roleId);
    }

    @Override
    public RolePermission getMappingById(Long id) {
        return rpRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mapping not found"));
    }

    @Override
    public void revokePermission(Long mappingId) {
        RolePermission rp = getMappingById(mappingId);
        rpRepo.deleteById(rp.getId());
    }
}
