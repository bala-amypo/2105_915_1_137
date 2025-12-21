package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.Role;
import com.example.demo.service.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

    
    @PostMapping
    public ApiResponse createRole(@RequestBody Role role) {
        Role saved = service.createRole(role);
        return new ApiResponse(true, "Role created successfully", saved);
    }

    
    @GetMapping
    public ApiResponse getAllRoles() {
        List<Role> list = service.getAllRoles();
        return new ApiResponse(true, "Roles fetched successfully", list);
    }
}
