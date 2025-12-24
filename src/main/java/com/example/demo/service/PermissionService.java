package com.example.demo.service;

import com.example.demo.entity.Permission;
import java.util.List;

public interface PermissionService {
    Permission createPermission(Permission permission);
    void deactivatePermission(Long id);
    List<Permission> getAllPermissions();
    Permission getPermissionById(Long id);
    Permission updatePermission(Long id, Permission permission);
    void deletePermission(Long id);
}