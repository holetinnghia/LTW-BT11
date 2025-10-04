package com.example.jwt.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/users")
public class UserController {
    @GetMapping("/me")
    public Object me(@AuthenticationPrincipal UserDetails u){
        return java.util.Map.of("username", u.getUsername(), "roles", u.getAuthorities());
    }
}