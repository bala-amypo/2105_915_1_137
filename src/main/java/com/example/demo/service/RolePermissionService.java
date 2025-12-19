package com.example.demo.service;

import com.example.demo.entity.RolePermission;

public interface RolePermissionService {

    RolePermission assignPermissionToRole(Long roleId, Long permissionId);
}