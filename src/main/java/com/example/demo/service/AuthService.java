Service
Authservice
package com.example.demo.service;

import com.example.demo.dto.AuthRequestDto;
import com.example.demo.dto.AuthResponseDto;
import com.example.demo.dto.RegisterRequestDto;

public interface AuthService {

    AuthResponseDto login(AuthRequestDto request);

    void register(RegisterRequestDto request);
}
userAccountservice
package com.example.demo.service;

import com.example.demo.entity.UserAccount;

import java.util.List;

public interface UserAccountService {

    UserAccount createUser(UserAccount user);

    List<UserAccount> getAllUsers();

    UserAccount getUserById(Long id);
}
Roleservice
package com.example.demo.service;

import com.example.demo.entity.Role;

import java.util.List;

public interface RoleService {

    Role createRole(Role role);

    List<Role> getAllRoles();

    Role getRoleById(Long id);
}
Permissionservice
package com.example.demo.service;

import com.example.demo.entity.Permission;

import java.util.List;

public interface PermissionService {

    Permission createPermission(Permission permission);

    List<Permission> getAllPermissions();

    Permission getPermissionById(Long id);
}
Rolepermissionservice
package com.example.demo.service;

import com.example.demo.entity.RolePermission;

public interface RolePermissionService {

    RolePermission assignPermissionToRole(Long roleId, Long permissionId);
}
Userroleservice
package com.example.demo.service;

import com.example.demo.entity.UserRole;

public interface UserRoleService {

    UserRole assignRoleToUser(Long userId, Long roleId);
}
Impl
Uthserviceimpl
package com.example.demo.service.impl;

import com.example.demo.dto.AuthRequestDto;
import com.example.demo.dto.AuthResponseDto;
import com.example.demo.dto.RegisterRequestDto;
import com.example.demo.entity.UserAccount;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.AuthService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserAccountRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authManager;

    public AuthServiceImpl(
            UserAccountRepository userRepo,
            PasswordEncoder passwordEncoder,
            JwtUtil jwtUtil,
            AuthenticationManager authManager) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authManager = authManager;
    }

    @Override
    public AuthResponseDto login(AuthRequestDto request) {

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        String token = jwtUtil.generateToken(request.getEmail());
        return new AuthResponseDto(token);
    }

    @Override
    public void register(RegisterRequestDto request) {

        if (userRepo.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email already exists");
        }

        UserAccount user = new UserAccount();
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());
        user.setPassword(
                passwordEncoder.encode(request.getPassword()));
        user.setActive(true);

        userRepo.save(user);
    }
}
Useraccountserviceimpl
package com.example.demo.service.impl;

import com.example.demo.entity.UserAccount;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.service.UserAccountService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userRepo;

    public UserAccountServiceImpl(UserAccountRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserAccount createUser(UserAccount user) {
        return userRepo.save(user);
    }

    @Override
    public List<UserAccount> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public UserAccount getUserById(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"));
    }
}
Roleserviceimpl
package com.example.demo.service.impl;

import com.example.demo.entity.Role;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.RoleService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepo;

    public RoleServiceImpl(RoleRepository roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public Role createRole(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepo.findAll();
    }

    @Override
    public Role getRoleById(Long id) {
        return roleRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Role not found"));
    }
}
Permissionserviceimpl
package com.example.demo.service.impl;

import com.example.demo.entity.Permission;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.PermissionRepository;
import com.example.demo.service.PermissionService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepo;

    public PermissionServiceImpl(
            PermissionRepository permissionRepo) {
        this.permissionRepo = permissionRepo;
    }

    @Override
    public Permission createPermission(Permission permission) {
        return permissionRepo.save(permission);
    }

    @Override
    public List<Permission> getAllPermissions() {
        return permissionRepo.findAll();
    }

    @Override
    public Permission getPermissionById(Long id) {
        return permissionRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Permission not found"));
    }
}
Rolepermissionserviceimpl
package com.example.demo.service.impl;

import com.example.demo.entity.Permission;
import com.example.demo.entity.Role;
import com.example.demo.entity.RolePermission;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.PermissionRepository;
import com.example.demo.repository.RolePermissionRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.RolePermissionService;

import org.springframework.stereotype.Service;

@Service
public class RolePermissionServiceImpl
        implements RolePermissionService {

    private final RoleRepository roleRepo;
    private final PermissionRepository permissionRepo;
    private final RolePermissionRepository rolePermissionRepo;

    public RolePermissionServiceImpl(
            RoleRepository roleRepo,
            PermissionRepository permissionRepo,
            RolePermissionRepository rolePermissionRepo) {
        this.roleRepo = roleRepo;
        this.permissionRepo = permissionRepo;
        this.rolePermissionRepo = rolePermissionRepo;
    }

    @Override
    public RolePermission assignPermissionToRole(
            Long roleId, Long permissionId) {

        Role role = roleRepo.findById(roleId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Role not found"));

        Permission permission =
                permissionRepo.findById(permissionId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Permission not found"));

        RolePermission rp =
                new RolePermission(role, permission);

        return rolePermissionRepo.save(rp);
    }
}
Userroleserviceimpl
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
