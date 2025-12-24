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

    @GetMapping
    public ResponseEntity<List<RolePermission>> getAllRolePermissions() {
        return ResponseEntity.ok(rolePermissionService.getAllRolePermissions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolePermission> getRolePermissionById(@PathVariable Long id) {
        return ResponseEntity.ok(rolePermissionService.getRolePermissionById(id));
    }

    @PostMapping
    public ResponseEntity<RolePermission> createRolePermission(@RequestBody RolePermission rolePermission) {
        return ResponseEntity.ok(rolePermissionService.createRolePermission(rolePermission));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolePermission> updateRolePermission(@PathVariable Long id, @RequestBody RolePermission rolePermission) {
        return ResponseEntity.ok(rolePermissionService.updateRolePermission(id, rolePermission));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRolePermission(@PathVariable Long id) {
        rolePermissionService.deleteRolePermission(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/role/{roleId}")
    public ResponseEntity<List<RolePermission>> getPermissionsByRole(@PathVariable Long roleId) {
        return ResponseEntity.ok(rolePermissionService.getPermissionsByRole(roleId));
    }
}