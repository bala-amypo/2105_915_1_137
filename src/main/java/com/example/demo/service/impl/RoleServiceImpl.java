package com.example.demo.service.impl;

import com.example.demo.entity.Role;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.RoleService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    
    private final RoleRepository roleRepository;
    
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    
    @Override
    public Role createRole(Role role) {
        if (roleRepository.findByRoleName(role.getRoleName()).isPresent()) {
            throw new BadRequestException("Role name already exists");
        }
        return roleRepository.save(role);
    }
    
    @Override
    public Role updateRole(Long id, Role role) {
        Role existing = roleRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Role not found"));
        
        existing.setRoleName(role.getRoleName());
        existing.setDescription(role.getDescription());
        
        return roleRepository.save(existing);
    }
    
    @Override
    public void deactivateRole(Long id) {
        Role role = roleRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Role not found"));
        role.setActive(false);
        roleRepository.save(role);
    }
    
    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
    
    @Override
    public Role getRoleById(Long id) {
        return roleRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Role not found"));
    }
    
    @Override
    public void deleteRole(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new ResourceNotFoundException("Role not found");
        }
        roleRepository.deleteById(id);
    }
}