package com.example.demo.dto;

public class AuthResponseDto {

    private String message;
    private String email;

    public AuthResponseDto() {}

    public AuthResponseDto(String message, String email) {
        this.message = message;
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public String getEmail() {
        return email;
    }
}
