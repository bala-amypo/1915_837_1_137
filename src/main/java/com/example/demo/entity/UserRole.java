package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name = "user_roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserAccount user;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    private Instant assignedAt;

    public UserRole(UserAccount user, Role role) {
        this.user = user;
        this.role = role;
    }

    @PrePersist
    public void prePersist() {
        this.assignedAt = Instant.now();
    }
}