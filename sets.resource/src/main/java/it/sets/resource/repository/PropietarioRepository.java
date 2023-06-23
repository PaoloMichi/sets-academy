package it.sets.resource.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.sets.resource.model.Propietario;

@Repository
public interface PropietarioRepository extends JpaRepository<Propietario, Long>{
	
	
public List<Propietario> findAll();
	
	public Optional<Propietario> findById(Long id);
	
	public Propietario save(Propietario propietario);
	
	public void deleteById(Long id);
	
	public List<Propietario> findByCasesIsNull();
	
	public List<Propietario> findByCasesIsNotNull();
}
