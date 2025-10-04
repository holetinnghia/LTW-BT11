package com.example.securitybasic.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean PasswordEncoder passwordEncoder() {
        // Delegating: hiểu {noop} và {bcrypt}
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean JdbcUserDetailsManager jdbcUserDetailsManager(DataSource ds) {
        return new JdbcUserDetailsManager(ds);
    }

    @Bean AuthenticationManager authenticationManager(
            JdbcUserDetailsManager uds, PasswordEncoder enc) {
        DaoAuthenticationProvider p = new DaoAuthenticationProvider();
        p.setUserDetailsService(uds);
        p.setPasswordEncoder(enc);
        return new ProviderManager(p);
    }

    @Bean SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(a -> a
                        .requestMatchers("/hello", "/login", "/error", "/assets/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin(f -> f.defaultSuccessUrl("/customers", true).permitAll())
                .logout(l -> l.logoutUrl("/logout").permitAll());
        return http.build();
    }
}