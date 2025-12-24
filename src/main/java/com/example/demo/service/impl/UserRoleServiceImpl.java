package com.example.demo.service.impl;

import com.example.demo.entity.UserRole;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRoleRepository;
import com.example.demo.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public UserRole assignRole(UserRole userRole) {
        if (!userRole.getUser().isActive()) {
            throw new BadRequestException("User is not active");
        }
        if (!userRole.getRole().isActive()) {
            throw new BadRequestException("Role is not active");
        }
        return userRoleRepository.save(userRole);
    }

    @Override
    public List<UserRole> getRolesForUser(Long userId) {
        return userRoleRepository.findByUser_Id(userId);
    }

    @Override
    public UserRole getMappingById(Long id) {
        return userRoleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User role mapping not found"));
    }

    @Override
    public void removeRole(Long id) {
        if (!userRoleRepository.existsById(id)) {
            throw new ResourceNotFoundException("User role mapping not found");
        }
        userRoleRepository.deleteById(id);
    }
}