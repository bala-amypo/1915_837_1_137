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

    private final PermissionRepository permissionRepository;

    public PermissionServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    // ===============================
    // CREATE PERMISSION
    // ===============================
    @Override
    public Permission createPermission(Permission permission) {

        if (permissionRepository.findByPermissionKey(
                permission.getPermissionKey()).isPresent()) {
            throw new BadRequestException("Permission already exists");
        }

        permission.setActive(true);
        return permissionRepository.save(permission);
    }

    // ===============================
    // UPDATE PERMISSION (AS REQUESTED)
    // ===============================
    @Override
    public Permission updatePermission(Long id, Permission updatedPermission) {

        Permission existing = permissionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Permission not found"));

        // Update ONLY fields that exist
        existing.setPermissionKey(updatedPermission.getPermissionKey());

        return permissionRepository.save(existing);
    }

    // ===============================
    // DEACTIVATE PERMISSION
    // ===============================
    @Override
    public void deactivatePermission(Long id) {

        Permission permission = permissionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Permission not found"));

        permission.setActive(false);
        permissionRepository.save(permission);
    }

    // ===============================
    // GET PERMISSION BY ID
    // ===============================
    @Override
    public Permission getPermissionById(Long id) {
        return permissionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Permission not found"));
    }

    // ===============================
    // GET ALL PERMISSIONS
    // ===============================
    @Override
    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }
}
