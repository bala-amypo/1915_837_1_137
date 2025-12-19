package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(
    name = "permissions",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "permissionKey")
    }
)
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String permissionKey;

    @Column
    private String description;

    @Column(nullable = false)
    private Boolean active = true;

    // ===== Constructors =====

    public Permission() {
    }

    public Permission(String permissionKey, String description, Boolean active) {
        this.permissionKey = permissionKey;
        this.description = description;
        this.active = active != null ? active : true;
    }

    // ===== Getters & Setters =====

    public Long getId() {
        return id;
    }

    public String getPermissionKey() {
        return permissionKey;
    }

    public void setPermissionKey(String permissionKey) {
        this.permissionKey = permissionKey;
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
