package com.example.demo.service.impl;

import com.example.demo.entity.Permission;
import com.example.demo.repository.PermissionRepository;
import com.example.demo.service.PermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository repo;

    public PermissionServiceImpl(PermissionRepository repo) {
        this.repo = repo;
    }

    public Permission create(Permission permission) {
        return repo.save(permission);
    }

    public List<Permission> getAll() {
        return repo.findAll();
    }
}
