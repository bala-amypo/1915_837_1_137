package com.example.demo.service.impl;

import com.example.demo.entity.Role;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service   // ✅ THIS IS MANDATORY
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    // ===============================
    // CREATE ROLE
    // ===============================
    @Override
    public Role createRole(Role role) {

        if (roleRepository.findByRoleName(role.getRoleName()).isPresent()) {
            throw new BadRequestException("Role already exists");
        }

        role.setActive(true);
        return roleRepository.save(role);
    }

    // ===============================
    // UPDATE ROLE
    // ===============================
    @Override
    public Role updateRole(Long id, Role updatedRole) {

        Role existing = roleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Role not found"));

        existing.setRoleName(updatedRole.getRoleName());
        existing.setDescription(updatedRole.getDescription());

        return roleRepository.save(existing);
    }

    // ===============================
    // DEACTIVATE ROLE
    // ===============================
    @Override
    public void deactivateRole(Long id) {

        Role role = roleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Role not found"));

        role.setActive(false);
        roleRepository.save(role);
    }

    // ===============================
    // GET ROLE BY ID
    // ===============================
    @Override
    public Role getRoleById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Role not found"));
    }

    // ===============================
    // GET ALL ROLES
    // ===============================
    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
