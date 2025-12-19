package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.RolePermissionService;
import org.springframework.stereotype.Service;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    private final RoleRepository roleRepo;
    private final PermissionRepository permRepo;
    private final RolePermissionRepository rpRepo;

    public RolePermissionServiceImpl(RoleRepository roleRepo,
                                     PermissionRepository permRepo,
                                     RolePermissionRepository rpRepo) {
        this.roleRepo = roleRepo;
        this.permRepo = permRepo;
        this.rpRepo = rpRepo;
    }

    public RolePermission assign(Long roleId, Long permissionId) {
        Role role = roleRepo.findById(roleId).orElseThrow();
        Permission perm = permRepo.findById(permissionId).orElseThrow();
        return rpRepo.save(new RolePermission(role, perm));
    }
}
