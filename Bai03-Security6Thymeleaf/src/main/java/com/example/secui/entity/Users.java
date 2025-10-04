// com/example/secui/entity/Users.java
package com.example.secui.entity;
import jakarta.persistence.*; import java.util.*;
@Entity @Table(name="users")
public class Users {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(unique=true,nullable=false,length=100) private String username;
    @Column(nullable=false,length=255) private String password;
    @Column(unique=true) private String email;
    @Column(nullable=false) private boolean enabled=true;
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="user_roles",
            joinColumns=@JoinColumn(name="user_id"),
            inverseJoinColumns=@JoinColumn(name="role_id"))
    private Set<Role> roles=new HashSet<>();
    // getters/setters
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getUsername(){return username;} public void setUsername(String v){this.username=v;}
    public String getPassword(){return password;} public void setPassword(String v){this.password=v;}
    public String getEmail(){return email;} public void setEmail(String v){this.email=v;}
    public boolean isEnabled(){return enabled;} public void setEnabled(boolean v){this.enabled=v;}
    public Set<Role> getRoles(){return roles;} public void setRoles(Set<Role> v){this.roles=v;}
}