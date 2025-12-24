package com.example.demo.service.impl;

import com.example.demo.entity.RolePermission;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.PermissionRepository;
import com.example.demo.repository.RolePermissionRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.RolePermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public RolePermission createRolePermission(RolePermission rolePermission) {
        return rolePermissionRepository.save(rolePermission);
    }

    @Override
    public RolePermission updateRolePermission(Long id, RolePermission updated) {
        RolePermission existing = rolePermissionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("RolePermission not found with id: " + id)
                );

        if (updated.getRole() != null) {
            existing.setRole(updated.getRole());
        }
        if (updated.getPermission() != null) {
            existing.setPermission(updated.getPermission());
        }

        return rolePermissionRepository.save(existing);
    }

    /** ✅ REQUIRED BY INTERFACE */
    @Override
    public RolePermission getRolePermissionById(Long id) {
        return rolePermissionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("RolePermission not found with id: " + id)
                );
    }

    @Override
    public List<RolePermission> getPermissionsByRole(Long roleId) {
        return rolePermissionRepository.findByRole_Id(roleId);
    }

    @Override
    public void deleteRolePermission(Long id) {
        if (!rolePermissionRepository.existsById(id)) {
            throw new ResourceNotFoundException("RolePermission not found with id: " + id);
        }
        rolePermissionRepository.deleteById(id);
    }
}
