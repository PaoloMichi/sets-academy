package it.sets.resource.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.sets.resource.model.Casa;

@Repository
public interface CasaRepository extends JpaRepository<Casa, Long> {
	
	public List<Casa> findAll();
	
	public Optional<Casa> findById(Long id);
	
	public Casa save(Casa casa);
	
	public void deleteById(Long id);

	public List<Casa> findByAffittuariIsNotNull();

	public List<Casa> findByAffittuariIsNull();
	

}
