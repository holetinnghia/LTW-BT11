package com.example.securitydb.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;
@Entity @Table(name="users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UserAccount {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false, unique=true, length=100) private String username;
    @Column(nullable=false, length=255) private String password;
    @Column(unique=true) private String email;
    @Column(nullable=false) private boolean enabled = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="user_roles",
            joinColumns=@JoinColumn(name="user_id"),
            inverseJoinColumns=@JoinColumn(name="role_id"))
    private Set<Role> roles = new HashSet<>();
}