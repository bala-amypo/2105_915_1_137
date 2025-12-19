package com.example.demo.service;

import com.example.demo.entity.RolePermission;

public interface RolePermissionService {
    RolePermission assign(Long roleId, Long permissionId);
}
