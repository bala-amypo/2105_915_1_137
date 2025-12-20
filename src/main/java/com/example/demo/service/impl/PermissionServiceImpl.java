package com.example.demo.service.impl;

import com.example.demo.entity.Permission;
import com.example.demo.repository.PermissionRepository;
import com.example.demo.service.PermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;

    public PermissionServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public Permission createPermission(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }

    @Override
    public Permission getPermissionById(Long id) {
        return permissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Permission not found"));
    }

    @Override
    public Permission updatePermission(Long id, Permission permission) {
        Permission existingPermission = getPermissionById(id);
        existingPermission.setName(permission.getName());
        existingPermission.setActive(permission.isActive());
        return permissionRepository.save(existingPermission);
    }

    @Override
    public void deactivatePermission(Long id) {
        Permission permission = getPermissionById(id);
        permission.setActive(false);
        permissionRepository.save(permission);
    }
}
