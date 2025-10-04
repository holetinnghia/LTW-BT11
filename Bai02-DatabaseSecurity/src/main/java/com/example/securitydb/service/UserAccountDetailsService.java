// service/UserAccountDetailsService.java
package com.example.securitydb.service;
import com.example.securitydb.repo.UserAccountRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service @RequiredArgsConstructor
public class UserAccountDetailsService implements UserDetailsService {
    private final UserAccountRepository users;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return users.findByUsername(username)
                .map(UserAccountDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
}