package com.example.demo.controller;

import com.example.demo.entity.Role;
import com.example.demo.entity.UserAccount;
import com.example.demo.entity.UserRole;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.repository.UserRoleRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-roles")
public class UserRoleController {

    private final UserRoleRepository userRoleRepository;
    private final UserAccountRepository userAccountRepository;
    private final RoleRepository roleRepository;

    
    public UserRoleController(UserRoleRepository userRoleRepository,
                              UserAccountRepository userAccountRepository,
                              RoleRepository roleRepository) {
        this.userRoleRepository = userRoleRepository;
        this.userAccountRepository = userAccountRepository;
        this.roleRepository = roleRepository;
    }

    
    @PostMapping
    public UserRole assignRole(@RequestParam Long userId,
                               @RequestParam Long roleId) {

        UserAccount user = userAccountRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Role not found"));

        UserRole userRole = new UserRole(user, role);
        return userRoleRepository.save(userRole);
    }

    
    @GetMapping("/user/{userId}")
    public List<UserRole> getRolesForUser(@PathVariable Long userId) {
        return userRoleRepository.findByUser_Id(userId);
    }
}
