package net.kacemi.j2eemvc.repository;

import net.kacemi.j2eemvc.entites.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByNameContainingIgnoreCase(String searchName);
}
