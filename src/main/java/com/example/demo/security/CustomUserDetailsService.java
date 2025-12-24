package com.example.demo.security;

import com.example.demo.entity.UserAccount;
import com.example.demo.entity.UserRole;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.repository.UserRoleRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    private final UserAccountRepository userAccountRepository;
    private final UserRoleRepository userRoleRepository;
    
    public CustomUserDetailsService(UserAccountRepository userAccountRepository,
                                  UserRoleRepository userRoleRepository) {
        this.userAccountRepository = userAccountRepository;
        this.userRoleRepository = userRoleRepository;
    }
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserAccount user = userAccountRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
        
        List<UserRole> userRoles = userRoleRepository.findByUser_Id(user.getId());
        List<GrantedAuthority> authorities = userRoles.stream()
            .map(userRole -> new SimpleGrantedAuthority(userRole.getRole().getRoleName()))
            .collect(Collectors.toList());
        
        return new User(user.getEmail(), user.getPassword() != null ? user.getPassword() : "", authorities);
    }
}