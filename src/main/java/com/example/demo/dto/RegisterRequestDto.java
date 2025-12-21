package com.example.demo.dto;

public class RegisterRequestDto {

    private String email;
    private String password;
    private String fullName;

    // No-args constructor
    public RegisterRequestDto() {
    }

    // All-args constructor
    public RegisterRequestDto(String email, String password, String fullName) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
    }

    // Getters & Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
