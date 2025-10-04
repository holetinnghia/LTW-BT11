// com/example/secui/repo/UserRepository.java
package com.example.secui.repo;
import com.example.secui.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface UserRepository extends JpaRepository<Users,Long>{
    Optional<Users> findByUsername(String username);
}