package com.example.demo.service.impl;

import com.example.demo.entity.UserRole;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.repository.UserRoleRepository;
import com.example.demo.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;
    private final UserAccountRepository userAccountRepository;
    private final RoleRepository roleRepository;

    public UserRoleServiceImpl(
            UserRoleRepository userRoleRepository,
            UserAccountRepository userAccountRepository,
            RoleRepository roleRepository
    ) {
        this.userRoleRepository = userRoleRepository;
        this.userAccountRepository = userAccountRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserRole createUserRole(UserRole userRole) {
        Long userId = userRole.getUser().getId();
        Long roleId = userRole.getRole().getId();

        var user = userAccountRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + userId)
                );

        var role = roleRepository.findById(roleId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Role not found with id: " + roleId)
                );

        if (!user.isActive()) {
            throw new BadRequestException("User is inactive");
        }
        if (!role.isActive()) {
            throw new BadRequestException("Role is inactive");
        }

        userRole.setUser(user);
        userRole.setRole(role);

        return userRoleRepository.save(userRole);
    }

    @Override
    public UserRole updateUserRole(Long id, UserRole updated) {
        UserRole existing = userRoleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("UserRole not found with id: " + id)
                );

        if (updated.getUser() != null) {
            existing.setUser(updated.getUser());
        }
        if (updated.getRole() != null) {
            existing.setRole(updated.getRole());
        }

        return userRoleRepository.save(existing);
    }

    @Override
    public List<UserRole> getRolesByUser(Long userId) {
        return userRoleRepository.findByUser_Id(userId);
    }

    /** ✅ MISSING METHOD — NOW IMPLEMENTED */
    @Override
    public UserRole getUserRoleById(Long id) {
        return userRoleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("UserRole not found with id: " + id)
                );
    }

    @Override
    public void deleteUserRole(Long id) {
        if (!userRoleRepository.existsById(id)) {
            throw new ResourceNotFoundException("UserRole not found with id: " + id);
        }
        userRoleRepository.deleteById(id);
    }
    
}
