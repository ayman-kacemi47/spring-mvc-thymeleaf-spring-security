package net.kacemi.j2eemvc.web;

import jakarta.validation.Valid;
import net.kacemi.j2eemvc.entites.Product;
import net.kacemi.j2eemvc.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    @GetMapping("/user/index")
    public String index(Model model) {
        model.addAttribute("listProducts", productRepository.findAll());

        return "products";
    }
    @GetMapping("/")
    public String home() {
        return "redirect:/user/index";
    }

    @GetMapping("/admin/deleteProduct")
    public String deleteProduct(@RequestParam(name = "id") Long id) {
        productRepository.deleteById(id);

        return "redirect:/user/index";
    }

    @GetMapping("/admin/add-product")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());

        return "add-product";
    }

    @PostMapping("/admin/saveProduct")
    public String saveProduct(@Valid  Product product, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "add-product";
        }
        productRepository.save(product);
        return "redirect:/admin/add-product";
    }

    @GetMapping("/admin/editProduct")
    public String editProduct(@RequestParam(name = "id") Long id, Model model) {
        model.addAttribute("product", productRepository.findById(id).orElse(null));
        return "edit-product";
    }

    @PostMapping("/updateProduct")
    public String updateProduct(@Valid Product product,  BindingResult bindingResult,  Model model) {
        if(bindingResult.hasErrors()) {
            return "edit-product";
        }

        productRepository.save(product);
        return "redirect:/";
    }

    @GetMapping("/user/filtredProducts")
    public String listProducts(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
        List<Product> products;
        if (keyword != null && !keyword.isEmpty()) {
            products = productRepository.findByNameContainingIgnoreCase(keyword);
        } else {
            products = productRepository.findAll();
        }
        model.addAttribute("listProducts", products);
        model.addAttribute("keyword", keyword); // pour garder le mot-clé dans l'input
        return "products";
    }


    @GetMapping("/not-authorized")
    public String notAuthorized() {
        return "notAuthorized";
    }

}

