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
    
    public RolePermissionServiceImpl(RolePermissionRepository rolePermissionRepository,
                                   RoleRepository roleRepository,
                                   PermissionRepository permissionRepository) {
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
    public List<RolePermission> getAllRolePermissions() {
        return rolePermissionRepository.findAll();
    }
    
    @Override
    public RolePermission getRolePermissionById(Long id) {
        return getMappingById(id);
    }
    
    @Override
    public RolePermission createRolePermission(RolePermission rolePermission) {
        return rolePermissionRepository.save(rolePermission);
    }
    
    @Override
    public RolePermission updateRolePermission(Long id, RolePermission rolePermission) {
        RolePermission existing = getMappingById(id);
        existing.setRole(rolePermission.getRole());
        existing.setPermission(rolePermission.getPermission());
        return rolePermissionRepository.save(existing);
    }
    
    @Override
    public void deleteRolePermission(Long id) {
        if (!rolePermissionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Role permission mapping not found");
        }
        rolePermissionRepository.deleteById(id);
    }
    
    @Override
    public List<RolePermission> getPermissionsByRole(Long roleId) {
        return getPermissionsForRole(roleId);
    }
}