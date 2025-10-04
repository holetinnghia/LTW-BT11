// com/example/secui/service/ProductService.java
package com.example.secui.service;
import com.example.secui.entity.Product; import com.example.secui.repo.ProductRepository;
import org.springframework.stereotype.Service; import java.util.List; import java.util.Optional;
@Service
public class ProductService {
    private final ProductRepository repo; public ProductService(ProductRepository r){this.repo=r;}
    public List<Product> findAll(){return repo.findAll();}
    public Optional<Product> findById(Long id){return repo.findById(id);}
    public Product save(Product p){return repo.save(p);}
    public void delete(Long id){repo.deleteById(id);}
}