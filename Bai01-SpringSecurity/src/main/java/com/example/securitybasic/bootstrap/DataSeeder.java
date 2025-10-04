package com.example.securitybasic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataSeeder {
    @Bean
    CommandLineRunner seedDefaultUsers(JdbcUserDetailsManager users, PasswordEncoder encoder) {
        return args -> {
            try {
                if (!users.userExists("admin")) {
                    UserDetails admin = User.withUsername("admin")
                            .password(encoder.encode("admin123"))
                            .roles("ADMIN","USER")
                            .build();
                    users.createUser(admin);
                }
            } catch (DuplicateKeyException ignored) {}
            try {
                if (!users.userExists("user")) {
                    UserDetails user = User.withUsername("user")
                            .password(encoder.encode("user123"))
                            .roles("USER")
                            .build();
                    users.createUser(user);
                }
            } catch (DuplicateKeyException ignored) {}
        };
    }
}