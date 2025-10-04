// com/example/secui/service/UserService.java
package com.example.secui.service;
import com.example.secui.entity.Users;
import com.example.secui.repo.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository users;
    public UserService(UserRepository users){this.users=users;}
    @Override public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users u = users.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return new org.springframework.security.core.userdetails.User(
                u.getUsername(), u.getPassword(), u.isEnabled(), true, true, true,
                u.getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toSet())
        );
    }
}