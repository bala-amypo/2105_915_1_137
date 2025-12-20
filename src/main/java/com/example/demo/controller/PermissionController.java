package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.Permission;
import com.example.demo.service.PermissionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    private final PermissionService service;

    public PermissionController(PermissionService service) {
        this.service = service;
    }

    // CREATE Permission
    @PostMapping
    public ApiResponse createPermission(@RequestBody Permission permission) {
        Permission saved = service.createPermission(permission);
        return new ApiResponse(true, "Permission created successfully", saved);
    }

    // GET ALL Permissions
    @GetMapping
    public ApiResponse getAllPermissions() {
        List<Permission> list = service.getAllPermissions();
        return new ApiResponse(true, "Permissions fetched successfully", list);
    }
}
