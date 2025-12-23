package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roleName;
    private String description;
    private Boolean active = true;

    public Role() {}

    public Role(String roleName, String description, Boolean active) {
        this.roleName = roleName;
        this.description = description;
        this.active = active;
    }

    // getters & setters
}
