// com/example/secui/config/SecurityConfig.java
package com.example.secui.config;
import com.example.secui.service.UserService;
import org.springframework.context.annotation.*; import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer; import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories; import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean PasswordEncoder passwordEncoder(){ return PasswordEncoderFactories.createDelegatingPasswordEncoder(); }
    @Bean AuthenticationManager authenticationManager(UserService uds, PasswordEncoder enc){
        DaoAuthenticationProvider p=new DaoAuthenticationProvider(); p.setUserDetailsService(uds); p.setPasswordEncoder(enc); return new ProviderManager(p);
    }
    @Bean SecurityFilterChain filter(HttpSecurity http) throws Exception{
        http.csrf(csrf->csrf.disable())
                .authorizeHttpRequests(a->a
                        .requestMatchers("/","/login","/css/**","/js/**","/images/**","/hello").permitAll()
                        .requestMatchers("/products/new","/products/edit/**","/products/delete/**").hasRole("ADMIN")
                        .requestMatchers("/products/**").hasAnyRole("ADMIN","USER")
                        .anyRequest().authenticated())
                .formLogin(f->f.loginPage("/login").defaultSuccessUrl("/products",true).permitAll())
                .logout(l->l.logoutUrl("/logout").logoutSuccessUrl("/").permitAll());
        return http.build();
    }
}