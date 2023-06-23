package it.sets.resource.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.sets.resource.model.Uomo;

@Repository
public interface UomoRepository extends JpaRepository<Uomo, Long> {
public List<Uomo> findAll();
	
	public Optional<Uomo> findById(Long id);
	
	public Uomo save(Uomo uomo);
	
	public void deleteById(Long id);
}
