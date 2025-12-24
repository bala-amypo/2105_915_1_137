package com.example.demo.service;

import com.example.demo.entity.Role;

public interface RoleService {
    Role createRole(Role role);
    Role updateRole(Long id, Role role);
    void deactivateRole(Long id);
}