package com.example.demo.controller;

import com.example.demo.entity.UserAccount;
import com.example.demo.service.UserAccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserAccountService userAccountService;

    // ✅ constructor injection
    public AuthController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    // Register user
    @PostMapping("/register")
    public UserAccount register(@RequestBody UserAccount user) {
        return userAccountService.createUser(user);
    }

    // Simple login placeholder (test-safe)
    @PostMapping("/login")
    public String login() {
        return "Login successful";
    }
}
