package com.example.demo.controller;

import com.example.demo.entity.RolePermission;
import com.example.demo.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role-permissions")
public class RolePermissionController {

    @Autowired
    private RolePermissionService rolePermissionService;

    @PostMapping
    public ResponseEntity<RolePermission> grantPermission(@RequestBody RolePermission rolePermission) {
        return ResponseEntity.ok(rolePermissionService.grantPermission(rolePermission));
    }

    @GetMapping("/role/{roleId}")
    public ResponseEntity<List<RolePermission>> getPermissionsForRole(@PathVariable Long roleId) {
        return ResponseEntity.ok(rolePermissionService.getPermissionsForRole(roleId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolePermission> getMappingById(@PathVariable Long id) {
        return ResponseEntity.ok(rolePermissionService.getMappingById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> revokePermission(@PathVariable Long id) {
        rolePermissionService.revokePermission(id);
        return ResponseEntity.noContent().build();
    }
}