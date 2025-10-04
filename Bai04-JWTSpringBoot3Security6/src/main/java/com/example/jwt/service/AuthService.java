package com.example.jwt.service;

import com.example.jwt.entity.Role;
import com.example.jwt.entity.User;
import com.example.jwt.repo.RoleRepository;
import com.example.jwt.repo.UserRepository;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthService {
    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UserRepository users;
    private final RoleRepository roles;
    private final PasswordEncoder enc;

    public AuthService(AuthenticationManager am, JwtService jwt, UserRepository ur, RoleRepository rr, PasswordEncoder enc){
        this.authManager=am; this.jwtService=jwt; this.users=ur; this.roles=rr; this.enc=enc;
    }

    public String login(String username, String password){
        Authentication a = authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        Map<String,Object> claims = Map.of("roles", a.getAuthorities().stream().map(Object::toString).toArray());
        return jwtService.generate(username, claims);
    }

    public void register(String username, String password, String email){
        if (users.existsByUsername(username)) throw new IllegalArgumentException("Username exists");
        Role userRole = roles.findByName("ROLE_USER").orElseGet(() -> roles.save(role("ROLE_USER")));
        User u = new User();
        u.setUsername(username);
        u.setPassword(enc.encode(password)); // BCrypt via delegating
        u.setEmail(email);
        u.setEnabled(true);
        u.getRoles().add(userRole);
        users.save(u);
    }

    private Role role(String name){ Role r=new Role(); r.setName(name); return r; }
}