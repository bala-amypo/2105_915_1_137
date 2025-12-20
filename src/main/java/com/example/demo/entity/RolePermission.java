package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class RolePermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "permission_id", nullable = false)
    private Permission permission;

    private Instant grantedAt;

    @PrePersist
    public void prePersist() {
        this.grantedAt = Instant.now();
    }

    // ======================
    // GETTERS
    // ======================

    public Long getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public Permission getPermission() {
        return permission;
    }

    public Instant getGrantedAt() {
        return grantedAt;
    }

    // ======================
    // SETTERS  ⭐ IMPORTANT ⭐
    // ======================

    public void setId(Long id) {
        this.id = id;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public void setGrantedAt(Instant grantedAt) {
        this.grantedAt = grantedAt;
    }
}
