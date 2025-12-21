package com.example.demo.dto;

public class AuthResponseDto {

    private String token;
    private String email;

    // No-args constructor
    public AuthResponseDto() {
    }

    // All-args constructor
    public AuthResponseDto(String token, String email) {
        this.token = token;
        this.email = email;
    }

    // Getters & Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
