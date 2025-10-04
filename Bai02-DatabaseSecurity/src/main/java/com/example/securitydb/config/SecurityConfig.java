// config/SecurityConfig.java
package com.example.securitydb.config;

import com.example.securitydb.service.UserAccountDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration @RequiredArgsConstructor
public class SecurityConfig {
    private final UserAccountDetailsService uds;

    @Bean PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder(); // hỗ trợ {bcrypt}
    }

    @Bean AuthenticationManager authenticationManager(PasswordEncoder enc) {
        DaoAuthenticationProvider p = new DaoAuthenticationProvider();
        p.setUserDetailsService(uds);
        p.setPasswordEncoder(enc);
        return new ProviderManager(p);
    }

    @Bean SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(a -> a
                        .requestMatchers("/hello","/error").permitAll()
                        .anyRequest().authenticated())
                .formLogin(f -> f
                        .defaultSuccessUrl("/profile", true) // ép về /profile sau khi login
                        .permitAll()
                )
                .logout(l -> l.logoutUrl("/logout").permitAll());
        return http.build();
    }
}