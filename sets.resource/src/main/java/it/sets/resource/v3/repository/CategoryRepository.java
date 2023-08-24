package it.sets.resource.v3.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.sets.resource.v3.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
    List<Category> findAll();
    
    Optional<Category> findById(Long id);
    
    Category save(Category category);
    
    void deleteById(Long id);
}
