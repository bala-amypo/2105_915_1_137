package com.example.demo.service.impl;

import com.example.demo.entity.UserRole;
import com.example.demo.repository.UserRoleRepository;
import com.example.demo.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository repository;

    public UserRoleServiceImpl(UserRoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserRole assignRole(UserRole userRole) {
        return repository.save(userRole);
    }

    @Override
    public List<UserRole> getRolesForUser(Long userId) {
        return repository.findByUser_Id(userId);
    }

    @Override
    public UserRole getMappingById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserRole mapping not found"));
    }

    @Override
    public void removeRole(Long id) {
        repository.deleteById(id);
    }
}
