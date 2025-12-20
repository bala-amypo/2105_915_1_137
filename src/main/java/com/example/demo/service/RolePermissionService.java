package com.example.demo.service;

import com.example.demo.entity.RolePermission;
@Service
public interface RolePermissionService {
    RolePermission assign(Long roleId, Long permissionId);
}
