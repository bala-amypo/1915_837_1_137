package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "role_permissions")
public class RolePermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Role role;

    @ManyToOne
    private Permission permission;

    private Instant grantedAt;

    public RolePermission() {}

    @PrePersist
    public void prePersist() {
        this.grantedAt = Instant.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
    public Permission getPermission() { return permission; }
    public void setPermission(Permission permission) { this.permission = permission; }
    public Instant getGrantedAt() { return grantedAt; }
}