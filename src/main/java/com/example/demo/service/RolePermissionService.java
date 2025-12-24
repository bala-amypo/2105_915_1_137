package com.example.demo.service;

import com.example.demo.entity.RolePermission;
import java.util.List;

public interface RolePermissionService {
    List<RolePermission> getPermissionsForRole(Long roleId);
    RolePermission getMappingById(Long id);
    List<RolePermission> getAllRolePermissions();
    RolePermission getRolePermissionById(Long id);
    RolePermission createRolePermission(RolePermission rolePermission);
    RolePermission updateRolePermission(Long id, RolePermission rolePermission);
    void deleteRolePermission(Long id);
    List<RolePermission> getPermissionsByRole(Long roleId);
}