package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(
    name = "roles",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "roleName")
    }
)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String roleName;

    @Column
    private String description;

    @Column(nullable = false)
    private Boolean active = true;

    // ===== Constructors =====

    public Role() {
    }

    public Role(String roleName, String description, Boolean active) {
        this.roleName = roleName;
        this.description = description;
        this.active = active != null ? active : true;
    }

    // ===== Getters & Setters =====

    public Long getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
