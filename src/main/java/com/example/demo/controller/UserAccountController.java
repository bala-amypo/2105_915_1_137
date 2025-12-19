package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.UserAccount;
import com.example.demo.service.UserAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserAccountController {

    private final UserAccountService service;

    public UserAccountController(UserAccountService service) {
        this.service = service;
    }

    @PostMapping
    public ApiResponse createUser(@RequestBody UserAccount user) {
        return new ApiResponse(
                true,
                "User created successfully",
                service.createUser(user)
        );
    }

    @GetMapping
    public ApiResponse getAllUsers() {
        List<UserAccount> users = service.getAllUsers();
        return new ApiResponse(true, "User list", users);
    }
}
