package com.example.demo.service.impl;

import com.example.demo.repository.RolePermissionRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.PermissionRepository;
import com.example.demo.service.RolePermissionService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.example.demo.entity.RolePermission;


@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    private final RolePermissionRepository rolePermissionRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public RolePermissionServiceImpl(
            RolePermissionRepository rolePermissionRepository,
            RoleRepository roleRepository,
            PermissionRepository permissionRepository
    ) {
        this.rolePermissionRepository = rolePermissionRepository;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public List<RolePermission> getPermissionsByRole(Long roleId) {
        return rolePermissionRepository.findByRole_Id(roleId);
    }
}
