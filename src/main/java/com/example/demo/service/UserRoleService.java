package com.example.demo.service;

import com.example.demo.entity.UserRole;
import java.util.List;

public interface UserRoleService {
    UserRole assignRole(UserRole userRole);
    List<UserRole> getRolesForUser(Long userId);
    UserRole getMappingById(Long id);
    void removeRole(Long id);
    List<UserRole> getAllUserRoles();
    UserRole getUserRoleById(Long id);
    UserRole createUserRole(UserRole userRole);
    UserRole updateUserRole(Long id, UserRole userRole);
    void deleteUserRole(Long id);
    List<UserRole> getRolesByUser(Long userId);
}