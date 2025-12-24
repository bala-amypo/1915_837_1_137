package com.example.demo.service.impl;

import com.example.demo.entity.Role;
import com.example.demo.entity.UserAccount;
import com.example.demo.entity.UserRole;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.repository.UserRoleRepository;
import com.example.demo.service.UserRoleService;

import java.util.List;

public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository urRepo;
    private final UserAccountRepository userRepo;
    private final RoleRepository roleRepo;

    public UserRoleServiceImpl(UserRoleRepository urRepo,
                               UserAccountRepository userRepo,
                               RoleRepository roleRepo) {
        this.urRepo = urRepo;
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    @Override
    public UserRole assignRole(UserRole mapping) {
        UserAccount user = userRepo.findById(mapping.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Role role = roleRepo.findById(mapping.getRole().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        if (!user.isActive()) {
            throw new BadRequestException("Inactive user");
        }
        if (!role.isActive()) {
            throw new BadRequestException("Inactive role");
        }

        return urRepo.save(mapping);
    }

    @Override
    public List<UserRole> getRolesForUser(Long userId) {
        return urRepo.findByUser_Id(userId);
    }

    @Override
    public UserRole getMappingById(Long id) {
        return urRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mapping not found"));
    }

    @Override
    public void removeRole(Long mappingId) {
        if (!urRepo.existsById(mappingId)) {
            throw new ResourceNotFoundException("Mapping not found");
        }
        urRepo.deleteById(mappingId);
    }
}
