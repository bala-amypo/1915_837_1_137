package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name = "role_permissions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolePermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "permission_id")
    private Permission permission;

    private Instant grantedAt;

    public RolePermission(Role role, Permission permission) {
        this.role = role;
        this.permission = permission;
    }

    @PrePersist
    public void prePersist() {
        this.grantedAt = Instant.now();
    }
}