package com.example.demo.service.impl;

import com.example.demo.entity.RolePermission;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.RolePermissionRepository;
import com.example.demo.service.RolePermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {
    private final RolePermissionRepository rolePermissionRepository;

    public RolePermissionServiceImpl(RolePermissionRepository rolePermissionRepository) {
        this.rolePermissionRepository = rolePermissionRepository;
    }

    @Override
    public List<RolePermission> getPermissionsForRole(Long roleId) {
        return rolePermissionRepository.findByRole_Id(roleId);
    }

    @Override
    public RolePermission getMappingById(Long id) {
        return rolePermissionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role permission mapping not found"));
    }

    @Override
    public RolePermission assignPermissionToRole(RolePermission rolePermission) {
        return rolePermissionRepository.save(rolePermission);
    }
}