package com.example.jwt.web;

import com.example.jwt.service.AuthService;
import com.example.jwt.web.dto.AuthDtos.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/auth")
public class AuthController {
    private final AuthService auth; public AuthController(AuthService a){this.auth=a;}

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest req){
        String token = auth.login(req.username, req.password);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req){
        auth.register(req.username, req.password, req.email);
        return ResponseEntity.ok().build();
    }
}