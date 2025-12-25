package com.example.demo.service.impl;

import com.example.demo.entity.Role;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.RoleService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;

    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Role createRole(Role role) {
        if (repository.findByRoleName(role.getRoleName()).isPresent()) {
            throw new BadRequestException("Role already exists");
        }
        return repository.save(role);
    }

    @Override
    public Role updateRole(Long id, Role role) {
        Role existing = getRoleById(id);
        existing.setRoleName(role.getRoleName());
        existing.setDescription(role.getDescription());
        return repository.save(existing);
    }

    @Override
    public Role getRoleById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));
    }

    @Override
    public List<Role> getAllRoles() {
        return repository.findAll();
    }

    @Override
    public void deactivateRole(Long id) {
        Role role = getRoleById(id);
        role.setActive(false);
        repository.save(role);
    }
}