package PizzaShop.Repositories;

import PizzaShop.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAll();
    List<Product> findAllByType(String type);
    List<Product> findAllByTypeAndStatus(String type, String status);
}