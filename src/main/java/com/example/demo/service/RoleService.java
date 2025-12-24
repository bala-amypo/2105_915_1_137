package com.example.demo.service;

import com.example.demo.entity.Role;
import java.util.List;

public interface RoleService {
    Role createRole(Role role);
    Role updateRole(Long id, Role role);
    void deactivateRole(Long id);
    List<Role> getAllRoles();
    Role getRoleById(Long id);
    void deleteRole(Long id);
}