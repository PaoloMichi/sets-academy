package it.sets.resource.v3.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.sets.resource.v3.model.Category;
import it.sets.resource.v3.model.ProductStored;
import it.sets.resource.v3.model.ProductStoredPk;

@Repository
public interface ProductStoredRepository extends JpaRepository<ProductStored, ProductStoredPk> {
    
   
	List<ProductStored> findAll();
    
    Optional<ProductStored> findById(ProductStoredPk id);
    
    Category save(Category category);
    
    void deleteById(Long id);

    List<ProductStored> findByStorageId (Long idStorage);
    
    List<ProductStored> findByAmountGreaterThanAndAmountIsNotNull(Integer amount);
}
