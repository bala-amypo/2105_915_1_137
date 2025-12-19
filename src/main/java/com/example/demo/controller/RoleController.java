package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.Role;
import com.example.demo.service.RoleService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

    @PostMapping
    public ApiResponse create(@RequestBody Role role) {
        return new ApiResponse(true, "Role created", service.create(role));
    }

    @GetMapping
    public ApiResponse list() {
        return new ApiResponse(true, "Roles", service.getAll());
    }
}
