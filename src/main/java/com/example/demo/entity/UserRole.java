package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(
    name = "user_roles",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "role_id"})
    }
)
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserAccount user;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Column(nullable = false, updatable = false)
    private Instant assignedAt;

    // ===== Constructors =====

    public UserRole() {
    }

    public UserRole(UserAccount user, Role role) {
        this.user = user;
        this.role = role;
    }

    // ===== Lifecycle Hooks =====

    @PrePersist
    protected void onCreate() {
        this.assignedAt = Instant.now();
    }

    // ===== Getters & Setters =====

    public Long getId() {
        return id;
    }

    public UserAccount getUser() {
        return user;
    }

    public Role getRole() {
        return role;
    }

    public Instant getAssignedAt() {
        return assignedAt;
    }
}
