package it.sets.resource.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.sets.resource.model.Couple;

@Repository
public interface CoupleRepository extends JpaRepository<Couple, Long> {

	public List<Couple> findAll();
	
	public Optional<Couple> findById(Long id);
	
	public Couple save(Couple couple);
	
	public void deleteById(Long id);
}
