package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name = "user_accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    private String fullName;
    private String password;
    private boolean active = true;

    private Instant createdAt;
    private Instant updatedAt;

    public UserAccount(String email, String fullName, Boolean active) {
        this.email = email;
        this.fullName = fullName;
        this.active = active != null ? active : true;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = Instant.now();
    }
}