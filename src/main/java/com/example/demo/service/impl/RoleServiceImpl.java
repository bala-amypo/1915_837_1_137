package com.example.demo.service.impl;

import com.example.demo.entity.Role;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.RoleService;

import java.util.List;

public class RoleServiceImpl implements RoleService {

    private final RoleRepository repo;

    public RoleServiceImpl(RoleRepository repo) {
        this.repo = repo;
    }

    @Override
    public Role createRole(Role role) {
        if (repo.findByRoleName(role.getRoleName()).isPresent()) {
            throw new BadRequestException("Role already exists");
        }
        return repo.save(role);
    }

    @Override
    public Role updateRole(Long id, Role role) {
        Role existing = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        existing.setRoleName(role.getRoleName());
        existing.setDescription(role.getDescription());
        return repo.save(existing);
    }

    @Override
    public Role getRoleById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));
    }

    @Override
    public List<Role> getAllRoles() {
        return repo.findAll();
    }

    @Override
    public void deactivateRole(Long id) {
        Role role = getRoleById(id);
        role.setActive(false);
        repo.save(role);
    }
}
