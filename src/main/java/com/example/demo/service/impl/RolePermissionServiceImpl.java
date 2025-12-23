package com.example.demo.service.impl;

import com.example.demo.entity.RolePermission;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.RolePermissionRepository;
import com.example.demo.service.RolePermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    private final RolePermissionRepository repo;

    public RolePermissionServiceImpl(RolePermissionRepository repo) {
        this.repo = repo;
    }

    @Override
    public RolePermission grantPermission(RolePermission mapping) {
        return repo.save(mapping);
    }

    @Override
    public List<RolePermission> getPermissionsForRole(Long roleId) {
        return repo.findByRole_Id(roleId);
    }

    @Override
    public RolePermission getMappingById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mapping not found"));
    }

    @Override
    public void revokePermission(Long id) {
        repo.deleteById(id);
    }
}
