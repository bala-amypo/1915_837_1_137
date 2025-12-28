package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String roleName;

    private String description;
    private boolean active = true;

    public Role(String roleName, String description, Boolean active) {
        this.roleName = roleName;
        this.description = description;
        this.active = active != null ? active : true;
    }
}