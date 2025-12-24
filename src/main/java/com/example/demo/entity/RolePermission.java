package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "role_permissions")
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
    
    @Column(name = "granted_at")
    private Instant grantedAt;
    
    @PrePersist
    public void prePersist() {
        this.grantedAt = Instant.now();
    }
    
    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
    
    public Permission getPermission() { return permission; }
    public void setPermission(Permission permission) { this.permission = permission; }
    
    public Instant getGrantedAt() { return grantedAt; }
    public void setGrantedAt(Instant grantedAt) { this.grantedAt = grantedAt; }
}