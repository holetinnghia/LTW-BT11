// com/example/secui/entity/Product.java
package com.example.secui.entity;
import jakarta.persistence.*; import java.math.BigDecimal; import java.time.OffsetDateTime;
@Entity @Table(name="products")
public class Product {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false) private String name;
    @Column(nullable=false,scale=2,precision=18) private BigDecimal price;
    @Column(name="created_at",nullable=false) private OffsetDateTime createdAt=OffsetDateTime.now();
    // getters/setters
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getName(){return name;} public void setName(String v){this.name=v;}
    public BigDecimal getPrice(){return price;} public void setPrice(BigDecimal v){this.price=v;}
    public OffsetDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(OffsetDateTime v){this.createdAt=v;}
}