package com.example.demo.service.impl;

import com.example.demo.entity.UserRole;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRoleRepository;
import com.example.demo.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    
    private final UserRoleRepository userRoleRepository;
    
    public UserRoleServiceImpl(UserRoleRepository userRoleRepository, 
                              UserAccountRepository userAccountRepository,
                              RoleRepository roleRepository) {
        this.userRoleRepository = userRoleRepository;
    }
    
    @Override
    public UserRole assignRole(UserRole userRole) {
        if (!userRole.getUser().isActive()) {
            throw new BadRequestException("Cannot assign role to inactive user");
        }
        if (!userRole.getRole().isActive()) {
            throw new BadRequestException("Cannot assign inactive role");
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
    
    @Override
    public List<UserRole> getAllUserRoles() {
        return userRoleRepository.findAll();
    }
    
    @Override
    public UserRole getUserRoleById(Long id) {
        return getMappingById(id);
    }
    
    @Override
    public UserRole createUserRole(UserRole userRole) {
        return assignRole(userRole);
    }
    
    @Override
    public UserRole updateUserRole(Long id, UserRole userRole) {
        UserRole existing = getMappingById(id);
        existing.setUser(userRole.getUser());
        existing.setRole(userRole.getRole());
        return userRoleRepository.save(existing);
    }
    
    @Override
    public void deleteUserRole(Long id) {
        removeRole(id);
    }
    
    @Override
    public List<UserRole> getRolesByUser(Long userId) {
        return getRolesForUser(userId);
    }
}