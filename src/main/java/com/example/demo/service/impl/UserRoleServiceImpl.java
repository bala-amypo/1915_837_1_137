package com.example.demo.service.impl;

import com.example.demo.entity.UserAccount;
import com.example.demo.entity.Role;
import com.example.demo.entity.UserRole;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.repository.UserRoleRepository;
import com.example.demo.service.UserRoleService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository repository;
    private final UserAccountRepository userAccountRepository;
    private final RoleRepository roleRepository;

    public UserRoleServiceImpl(UserRoleRepository repository, UserAccountRepository userAccountRepository, RoleRepository roleRepository) {
        this.repository = repository;
        this.userAccountRepository = userAccountRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserRole assignRole(UserRole mapping) {
        UserAccount u = userAccountRepository.findById(mapping.getUser().getId()).orElseThrow();
        Role r = roleRepository.findById(mapping.getRole().getId()).orElseThrow();

        if (!u.isActive() || !r.isActive()) {
            throw new BadRequestException("Inactive user or role");
        }
        return repository.save(mapping);
    }

    @Override
    public List<UserRole> getRolesForUser(Long userId) {
        return repository.findByUser_Id(userId);
    }

    @Override
    public UserRole getMappingById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mapping not found"));
    }

    @Override
    public void removeRole(Long mappingId) {
        if (!repository.existsById(mappingId)) {
            throw new ResourceNotFoundException("Mapping not found");
        }
        repository.deleteById(mappingId);
    }
}