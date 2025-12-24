package com.example.demo.service;

import com.example.demo.entity.Permission;

public interface PermissionService {
    Permission createPermission(Permission permission);
    void deactivatePermission(Long id);
}