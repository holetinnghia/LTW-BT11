// com/example/secui/web/ProductController.java
package com.example.secui.web;
import com.example.secui.entity.Product; import com.example.secui.service.ProductService;
import org.springframework.stereotype.Controller; import org.springframework.ui.Model; import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService svc; public ProductController(ProductService s){this.svc=s;}
    @GetMapping public String list(Model m){ m.addAttribute("products", svc.findAll()); return "index"; }
    @GetMapping("/new") public String newForm(Model m){ m.addAttribute("product", new Product()); return "new_product"; }
    @PostMapping public String create(@ModelAttribute Product p){ svc.save(p); return "redirect:/products"; }
    @GetMapping("/edit/{id}") public String edit(@PathVariable Long id, Model m){
        m.addAttribute("product", svc.findById(id).orElseThrow()); return "edit_product"; }
    @PostMapping("/edit/{id}") public String update(@PathVariable Long id, @ModelAttribute Product p){
        p.setId(id); svc.save(p); return "redirect:/products"; }
    @PostMapping("/delete/{id}") public String delete(@PathVariable Long id){ svc.delete(id); return "redirect:/products"; }
}