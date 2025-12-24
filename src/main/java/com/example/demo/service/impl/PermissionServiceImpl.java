package com.example.demo.service.impl;

import com.example.demo.entity.Permission;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.PermissionRepository;
import com.example.demo.service.PermissionService;

import java.util.List;

public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository repo;

    public PermissionServiceImpl(PermissionRepository repo) {
        this.repo = repo;
    }

    @Override
    public Permission createPermission(Permission permission) {
        if (repo.findByPermissionKey(permission.getPermissionKey()).isPresent()) {
            throw new BadRequestException("Permission exists");
        }
        return repo.save(permission);
    }

    @Override
    public Permission updatePermission(Long id, Permission permission) {
        Permission existing = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Permission not found"));

        existing.setPermissionKey(permission.getPermissionKey());
        return repo.save(existing);
    }

    @Override
    public Permission getPermissionById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Permission not found"));
    }

    @Override
    public List<Permission> getAllPermissions() {
        return repo.findAll();
    }

    @Override
    public void deactivatePermission(Long id) {
        Permission permission = getPermissionById(id);
        permission.setActive(false);
        repo.save(permission);
    }
}
