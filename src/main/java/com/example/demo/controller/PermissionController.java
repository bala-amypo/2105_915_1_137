package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.Permission;
import com.example.demo.service.PermissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Permission>> createPermission(@RequestBody Permission permission) {
        Permission created = permissionService.createPermission(permission);
        return ResponseEntity.ok(ApiResponse.success(created));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deactivatePermission(@PathVariable Long id) {
        permissionService.deactivatePermission(id);
        return ResponseEntity.ok(ApiResponse.success("Permission deactivated successfully"));
    }
}