package com.example.demo.service;

import com.example.demo.entity.UserRole;
import java.util.List;

public interface UserRoleService {

    UserRole assignRole(Long userId, Long roleId);

    List<UserRole> getRolesForUser(Long userId);
}
