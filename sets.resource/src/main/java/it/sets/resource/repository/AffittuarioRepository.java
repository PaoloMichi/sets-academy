package it.sets.resource.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.sets.resource.model.Affittuario;

@Repository
public interface AffittuarioRepository extends JpaRepository<Affittuario, Long>{

	public List<Affittuario> findAll();
	
	public Optional<Affittuario> findById(Long id);
	
	public Affittuario save(Affittuario affittuario);
	
	public void deleteById(Long id);

	public List<Affittuario> findByCasa_IdIsNotNull();

	public List<Affittuario> findByCasa_IdIsNull();
}
