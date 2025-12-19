package com.example.demo.service.impl;

import com.example.demo.entity.Permission;
import com.example.demo.entity.Role;
import com.example.demo.entity.RolePermission;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.PermissionRepository;
import com.example.demo.repository.RolePermissionRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.RolePermissionService;

import org.springframework.stereotype.Service;

@Service
public class RolePermissionServiceImpl
        implements RolePermissionService {

    private final RoleRepository roleRepo;
    private final PermissionRepository permissionRepo;
    private final RolePermissionRepository rolePermissionRepo;

    public RolePermissionServiceImpl(
            RoleRepository roleRepo,
            PermissionRepository permissionRepo,
            RolePermissionRepository rolePermissionRepo) {
        this.roleRepo = roleRepo;
        this.permissionRepo = permissionRepo;
        this.rolePermissionRepo = rolePermissionRepo;
    }

    @Override
    public RolePermission assignPermissionToRole(
            Long roleId, Long permissionId) {

        Role role = roleRepo.findById(roleId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Role not found"));

        Permission permission =
                permissionRepo.findById(permissionId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Permission not found"));

        RolePermission rp =
                new RolePermission(role, permission);

        return rolePermissionRepo.save(rp);
    }
}