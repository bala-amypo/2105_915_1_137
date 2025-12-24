package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "user_roles")
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
    
    @Column(name = "assigned_at")
    private Instant assignedAt;
    
    @PrePersist
    public void prePersist() {
        this.assignedAt = Instant.now();
    }
    
    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public UserAccount getUser() { return user; }
    public void setUser(UserAccount user) { this.user = user; }
    
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
    
    public Instant getAssignedAt() { return assignedAt; }
    public void setAssignedAt(Instant assignedAt) { this.assignedAt = assignedAt; }
}