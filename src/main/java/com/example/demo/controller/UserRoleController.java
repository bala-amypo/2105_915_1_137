package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.UserRole;
import com.example.demo.service.UserRoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-roles")
public class UserRoleController {

    private final UserRoleService userRoleService;

    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserRole>> assignRole(@RequestBody UserRole userRole) {
        UserRole assigned = userRoleService.assignRole(userRole);
        return ResponseEntity.ok(ApiResponse.success(assigned));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<UserRole>>> getRolesForUser(@PathVariable Long userId) {
        List<UserRole> roles = userRoleService.getRolesForUser(userId);
        return ResponseEntity.ok(ApiResponse.success(roles));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserRole>> getMappingById(@PathVariable Long id) {
        UserRole mapping = userRoleService.getMappingById(id);
        return ResponseEntity.ok(ApiResponse.success(mapping));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> removeRole(@PathVariable Long id) {
        userRoleService.removeRole(id);
        return ResponseEntity.ok(ApiResponse.success("Role removed successfully"));
    }
}