package com.example.demo.service;

import com.example.demo.entity.Permission;
import java.util.List;
@Service
public interface PermissionService {
    Permission create(Permission permission);
    List<Permission> getAll();
}
