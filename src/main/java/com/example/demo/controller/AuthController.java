package com.example.demo.controller;

import com.example.demo.entity.UserAccount;
import com.example.demo.service.UserAccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserAccountService userAccountService;

    
    public AuthController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

   
    @PostMapping("/register")
    public UserAccount register(@RequestBody UserAccount user) {
        return userAccountService.createUser(user);
    }

    
    @PostMapping("/login")
    public String login() {
        return "Login successful";
    }
}
