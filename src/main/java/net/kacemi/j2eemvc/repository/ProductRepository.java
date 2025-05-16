package net.kacemi.j2eemvc.repository;

import net.kacemi.j2eemvc.entites.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {

}
