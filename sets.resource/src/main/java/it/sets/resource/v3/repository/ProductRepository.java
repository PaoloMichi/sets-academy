package it.sets.resource.v3.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.sets.resource.v3.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
    List<Product> findAll();
    
    Optional<Product> findById(Long id);
    
    Product save(Product product);
    
    void deleteById(Long id);
}
