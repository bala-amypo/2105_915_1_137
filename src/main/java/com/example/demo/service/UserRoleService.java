package com.example.demo.service;

import com.example.demo.entity.UserRole;

public interface UserRoleService {

    UserRole assignRoleToUser(Long userId, Long roleId);
}