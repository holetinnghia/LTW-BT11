package com.example.jwt.web.dto;
public class AuthDtos {
    public static class LoginRequest { public String username; public String password; }
    public static class RegisterRequest { public String username; public String password; public String email; }
    public static class AuthResponse { public String token; public AuthResponse(String t){this.token=t;} }
}