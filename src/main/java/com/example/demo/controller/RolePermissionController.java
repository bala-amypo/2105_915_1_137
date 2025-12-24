package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.RolePermission;
import com.example.demo.service.RolePermissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role-permissions")
public class RolePermissionController {

    private final RolePermissionService rolePermissionService;

    public RolePermissionController(RolePermissionService rolePermissionService) {
        this.rolePermissionService = rolePermissionService;
    }

    @GetMapping("/role/{roleId}")
    public ResponseEntity<ApiResponse<List<RolePermission>>> getPermissionsForRole(@PathVariable Long roleId) {
        List<RolePermission> permissions = rolePermissionService.getPermissionsForRole(roleId);
        return ResponseEntity.ok(ApiResponse.success(permissions));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RolePermission>> getMappingById(@PathVariable Long id) {
        RolePermission mapping = rolePermissionService.getMappingById(id);
        return ResponseEntity.ok(ApiResponse.success(mapping));
    }
}