package com.example.demo.security;

import com.example.demo.entity.UserAccount;
import com.example.demo.entity.UserRole;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.repository.UserRoleRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;

import java.util.List;
import java.util.stream.Collectors;

public class CustomUserDetailsService implements UserDetailsService {

    private final UserAccountRepository userRepo;
    private final UserRoleRepository userRoleRepo;

    public CustomUserDetailsService(UserAccountRepository userRepo,
                                    UserRoleRepository userRoleRepo) {
        this.userRepo = userRepo;
        this.userRoleRepo = userRoleRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        UserAccount user = userRepo.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));

        List<UserRole> roles = userRoleRepo.findByUser_Id(user.getId());

        return new User(
                user.getEmail(),
                user.getPassword() == null ? "" : user.getPassword(),
                roles.stream()
                        .map(r -> new SimpleGrantedAuthority(r.getRole().getRoleName()))
                        .collect(Collectors.toList())
        );
    }
}
