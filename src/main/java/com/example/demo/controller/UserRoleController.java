package com.example.demo.controller;

import com.example.demo.entity.UserRole;
import com.example.demo.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-roles")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @PostMapping
    public ResponseEntity<UserRole> assignRole(@RequestBody UserRole userRole) {
        return ResponseEntity.ok(userRoleService.assignRole(userRole));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserRole>> getRolesForUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userRoleService.getRolesForUser(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRole> getMappingById(@PathVariable Long id) {
        return ResponseEntity.ok(userRoleService.getMappingById(id));
    }

    
}