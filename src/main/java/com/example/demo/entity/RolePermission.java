package com.example.demo.entity;

import jakarta.persistence.*;

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

    public RolePermission() {}

    public RolePermission(Role role, Permission permission) {
        this.role = role;
        this.permission = permission;
    }

    public Long getId() { return id; }

    public Role getRole() { return role; }

    public Permission getPermission() { return permission; }
}
