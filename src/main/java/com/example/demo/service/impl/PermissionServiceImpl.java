package com.example.demo.service.impl;

import com.example.demo.entity.Permission;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.PermissionRepository;
import com.example.demo.service.PermissionService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepo;

    public PermissionServiceImpl(
            PermissionRepository permissionRepo) {
        this.permissionRepo = permissionRepo;
    }

    @Override
    public Permission createPermission(Permission permission) {
        return permissionRepo.save(permission);
    }

    @Override
    public List<Permission> getAllPermissions() {
        return permissionRepo.findAll();
    }

    @Override
    public Permission getPermissionById(Long id) {
        return permissionRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Permission not found"));
    }
}