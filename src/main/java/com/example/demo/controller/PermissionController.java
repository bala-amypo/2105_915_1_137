package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.Permission;
import com.example.demo.service.PermissionService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    private final PermissionService service;

    public PermissionController(PermissionService service) {
        this.service = service;
    }

    @PostMapping
    public ApiResponse create(@RequestBody Permission p) {
        return new ApiResponse(true, "Permission created", service.create(p));
    }

    @GetMapping
    public ApiResponse list() {
        return new ApiResponse(true, "Permissions", service.getAll());
    }
}
service.createPermission(permission);
service.getAllPermissions();
