package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "permissions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String permissionKey;

    private String description;
    private boolean active = true;

    public Permission(String permissionKey, String description, Boolean active) {
        this.permissionKey = permissionKey;
        this.description = description;
        this.active = active != null ? active : true;
    }
}