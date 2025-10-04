// com/example/secui/repo/ProductRepository.java
package com.example.secui.repo;
import com.example.secui.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProductRepository extends JpaRepository<Product,Long>{}