package com.example.demo.service.impl;

import com.example.demo.entity.RolePermission;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.PermissionRepository;
import com.example.demo.repository.RolePermissionRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.RolePermissionService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {
    private final RolePermissionRepository repository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public RolePermissionServiceImpl(RolePermissionRepository repository, RoleRepository roleRepository, PermissionRepository permissionRepository) {
        this.repository = repository;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public RolePermission grantPermission(RolePermission mapping) {
        return repository.save(mapping);
    }

    @Override
    public List<RolePermission> getPermissionsForRole(Long roleId) {
        return repository.findByRole_Id(roleId);
    }

    @Override
    public RolePermission getMappingById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mapping not found"));
    }

    @Override
    public void revokePermission(Long mappingId) {
        if (!repository.existsById(mappingId)) {
             throw new ResourceNotFoundException("Mapping not found");
        }
        repository.deleteById(mappingId);
    }
}