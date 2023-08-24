package it.sets.resource.v3.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.sets.resource.v3.model.Subcategory;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {
	
    List<Subcategory> findAll();
    
    Optional<Subcategory> findById(Long id);
    
    Subcategory save(Subcategory subcategory);
    
    void deleteById(Long id);
}
