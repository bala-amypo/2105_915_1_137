package com.example.demo.service.impl;

import com.example.demo.entity.UserAccount;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserAccountRepository userAccountRepository;

    // ✅ constructor
    public AuthServiceImpl(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public UserAccount register(UserAccount user) {

        if (userAccountRepository.existsByEmail(user.getEmail())) {
            throw new BadRequestException("Email already exists");
        }

        return userAccountRepository.save(user);
    }

    @Override
    public String login(String email, String password) {

        UserAccount user = userAccountRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Invalid credentials"));

        // ⚠️ password check placeholder (safe for now)
        if (user.getPassword() == null ||
                !user.getPassword().equals(password)) {
            throw new BadRequestException("Invalid credentials");
        }

        return "LOGIN_SUCCESS";
    }
}
