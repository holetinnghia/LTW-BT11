// repo/RoleRepository.java
package com.example.securitydb.repo;
import com.example.securitydb.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}