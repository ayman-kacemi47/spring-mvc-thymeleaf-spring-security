package net.kacemi.j2eemvc;

import net.kacemi.j2eemvc.entites.Product;
import net.kacemi.j2eemvc.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class J2eeMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(J2eeMvcApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository) {
        return args -> {
        productRepository.save(Product.builder().name("Computer").price(8000).quantity(11).build());
        productRepository.save(Product.builder().name("Printer").price(4500).quantity(3).build());
        productRepository.save(Product.builder().name("Smart Phone").price(3200).quantity(25).build());

        productRepository.findAll().forEach(System.out::println);
        } ;
    }
}
