package com.example.demo.service;

import com.example.demo.entity.RolePermission;
import java.util.List;

public interface RolePermissionService {
    List<RolePermission> getPermissionsForRole(Long roleId);
    RolePermission getMappingById(Long id);
}