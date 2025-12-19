package com.example.demo.service.impl;

import com.example.demo.entity.Role;
import com.example.demo.entity.UserAccount;
import com.example.demo.entity.UserRole;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.repository.UserRoleRepository;
import com.example.demo.service.UserRoleService;

import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserAccountRepository userRepo;
    private final RoleRepository roleRepo;
    private final UserRoleRepository userRoleRepo;

    public UserRoleServiceImpl(
            UserAccountRepository userRepo,
            RoleRepository roleRepo,
            UserRoleRepository userRoleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.userRoleRepo = userRoleRepo;
    }

    @Override
    public UserRole assignRoleToUser(
            Long userId, Long roleId) {

        UserAccount user =
                userRepo.findById(userId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "User not found"));

        Role role =
                roleRepo.findById(roleId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Role not found"));

        UserRole userRole =
                new UserRole(user, role);

        return userRoleRepo.save(userRole);
    }
}
