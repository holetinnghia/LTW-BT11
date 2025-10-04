// bootstrap/DataSeeder.java
package com.example.securitydb.bootstrap;

import com.example.securitydb.entity.Role;
import com.example.securitydb.entity.UserAccount;
import com.example.securitydb.repo.RoleRepository;
import com.example.securitydb.repo.UserAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class DataSeeder {
    @Bean
    CommandLineRunner seed(RoleRepository roles, UserAccountRepository users, PasswordEncoder enc){
        return args -> {
            Role admin = roles.findByName("ROLE_ADMIN").orElseGet(() -> roles.save(Role.builder().name("ROLE_ADMIN").build()));
            Role user  = roles.findByName("ROLE_USER").orElseGet(() -> roles.save(Role.builder().name("ROLE_USER").build()));

            if (!users.existsByUsername("admin")) {
                users.save(UserAccount.builder()
                        .username("admin").password(enc.encode("admin123")).email("admin@example.com")
                        .roles(Set.of(admin, user)).enabled(true).build());
            }
            if (!users.existsByUsername("user")) {
                users.save(UserAccount.builder()
                        .username("user").password(enc.encode("user123")).email("user@example.com")
                        .roles(Set.of(user)).enabled(true).build());
            }
        };
    }
}