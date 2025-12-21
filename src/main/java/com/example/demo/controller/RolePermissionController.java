package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.RolePermission;
import com.example.demo.service.RolePermissionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role-permissions")
public class RolePermissionController {

    private final RolePermissionService service;

    public RolePermissionController(RolePermissionService service) {
        this.service = service;
    }

    
    @PostMapping
    public ApiResponse assignPermission(@RequestBody RolePermission mapping) {
        RolePermission saved = service.grantPermission(mapping);
        return new ApiResponse(true, "Permission assigned to role", saved);
    }

    
    @GetMapping("/role/{roleId}")
    public ApiResponse getPermissionsByRole(@PathVariable Long roleId) {
        List<RolePermission> list = service.getPermissionsForRole(roleId);
        return new ApiResponse(true, "Role permissions fetched", list);
    }
}
