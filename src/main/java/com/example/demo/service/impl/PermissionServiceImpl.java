package com.example.demo.service.impl;

import com.example.demo.entity.Permission;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.PermissionRepository;
import com.example.demo.service.PermissionService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository repository;

    public PermissionServiceImpl(PermissionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Permission createPermission(Permission permission) {
        if (repository.findByPermissionKey(permission.getPermissionKey()).isPresent()) {
            throw new BadRequestException("Permission already exists");
        }
        return repository.save(permission);
    }

    @Override
    public Permission updatePermission(Long id, Permission permission) {
        Permission existing = getPermissionById(id);
        existing.setPermissionKey(permission.getPermissionKey());
        return repository.save(existing);
    }

    @Override
    public Permission getPermissionById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Permission not found"));
    }

    @Override
    public List<Permission> getAllPermissions() {
        return repository.findAll();
    }

    @Override
    public void deactivatePermission(Long id) {
        Permission p = getPermissionById(id);
        p.setActive(false);
        repository.save(p);
    }
}