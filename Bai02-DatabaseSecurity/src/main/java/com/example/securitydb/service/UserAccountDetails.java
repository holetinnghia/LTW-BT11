// service/UserAccountDetails.java
package com.example.securitydb.service;
import com.example.securitydb.entity.UserAccount;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Set;
import java.util.stream.Collectors;

public record UserAccountDetails(UserAccount u) implements UserDetails {
    @Override public Set<GrantedAuthority> getAuthorities() {
        return u.getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toSet());
    }
    @Override public String getPassword(){ return u.getPassword(); }
    @Override public String getUsername(){ return u.getUsername(); }
    @Override public boolean isAccountNonExpired(){ return true; }
    @Override public boolean isAccountNonLocked(){ return true; }
    @Override public boolean isCredentialsNonExpired(){ return true; }
    @Override public boolean isEnabled(){ return u.isEnabled(); }
}