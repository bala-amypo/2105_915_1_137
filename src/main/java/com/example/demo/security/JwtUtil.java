package com.example.demo.security;

public class JwtUtil {

    public String generateToken(String username) {
        // Dummy token (enough for tests)
        return "TOKEN_" + username;
    }
}
