package net.kacemi.j2eemvc.web;

import net.kacemi.j2eemvc.entites.Product;
import net.kacemi.j2eemvc.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ProductController {
    private ProductRepository productRepository;

    public ProductController( ProductRepository productRepository) {

        this.productRepository = productRepository;
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("listProducts", productRepository.findAll());

        return "products";
    }
    @GetMapping("/")
    public String home() {
        return "redirect:/index";
    }

    @GetMapping("/deleteProduct")
    public String deleteProduct(@RequestParam(name = "id") Long id) {
        productRepository.deleteById(id);
        return "redirect:/index";
    }

    @GetMapping("/add-product")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());

        return "add-product";
    }

    @PostMapping("/saveProduct")
    public String saveProduct(Product product) {
        productRepository.save(product);
        return "redirect:/add-product";
    }
}
