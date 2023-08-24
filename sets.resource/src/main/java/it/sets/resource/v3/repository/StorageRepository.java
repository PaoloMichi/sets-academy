package it.sets.resource.v3.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.sets.resource.v3.model.Storage;

@Repository
public interface StorageRepository extends JpaRepository<Storage, Long> {
	
    List<Storage> findAll();
    
    Optional<Storage> findById(Long id);
    
    Storage save(Storage storage);
    
    void deleteById(Long id);
}
