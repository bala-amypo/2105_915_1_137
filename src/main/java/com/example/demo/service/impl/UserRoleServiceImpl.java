package com.example.demo.service.impl;

import com.example.demo.repository.UserRoleRepository;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.UserRoleService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.example.demo.entity.UserRole;
import com.example.demo.exception.ResourceNotFoundException;

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
    public List<UserRole> getRolesByUser(Long userId) {
        return userRoleRepository.findByUser_Id(userId);
    }
    @Override
public void deleteUserRole(Long id) {
    if (!userRoleRepository.existsById(id)) {
        throw new ResourceNotFoundException("UserRole not found with id: " + id);
    }
    userRoleRepository.deleteById(id);
}
}
