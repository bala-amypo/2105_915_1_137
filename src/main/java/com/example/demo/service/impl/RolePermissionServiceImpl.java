package com.example.demo.service.impl;

import com.example.demo.entity.RolePermission;
import com.example.demo.repository.RolePermissionRepository;
import com.example.demo.service.RolePermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    private final RolePermissionRepository repository;

    public RolePermissionServiceImpl(RolePermissionRepository repository) {
        this.repository = repository;
    }

    @Override
    public RolePermission grantPermission(RolePermission mapping) {
        return repository.save(mapping);
    }

    @Override
    public List<RolePermission> getPermissionsForRole(Long roleId) {
        return repository.findByRole_Id(roleId);
    }

    @Override
    public RolePermission getMappingById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("RolePermission mapping not found"));
    }

    @Override
    public void revokePermission(Long id) {
        repository.deleteById(id);
    }
}
