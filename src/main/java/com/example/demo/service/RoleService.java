package com.example.demo.service;

import com.example.demo.entity.Role;
import java.util.List;

public interface RoleService {
    Role create(Role role);
    List<Role> getAll();
}




