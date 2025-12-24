package com.example.demo.service.impl;

import com.example.demo.entity.Permission;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.PermissionRepository;
import com.example.demo.service.PermissionService;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository permissionRepository;

    public PermissionServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public Permission createPermission(Permission permission) {
        if (permissionRepository.findByPermissionKey(permission.getPermissionKey()).isPresent()) {
            throw new BadRequestException("Permission key already exists");
        }
        return permissionRepository.save(permission);
    }

    @Override
    public void deactivatePermission(Long id) {
        Permission permission = permissionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Permission not found"));
        permission.setActive(false);
        permissionRepository.save(permission);
    }
}