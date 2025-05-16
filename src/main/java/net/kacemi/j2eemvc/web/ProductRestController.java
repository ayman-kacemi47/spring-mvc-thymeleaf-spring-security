package net.kacemi.j2eemvc.web;

import jakarta.annotation.Resource;
import net.kacemi.j2eemvc.entites.Product;
import net.kacemi.j2eemvc.repository.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductRestController {
    private ProductRepository productRepository;

    public ProductRestController( ProductRepository productRepository) {
        this.productRepository = productRepository;
    }



    @GetMapping("/products")
    public List<Product> getProducts() {
        return  productRepository.findAll();
    }

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productRepository.findById(id).get();
    }
}
