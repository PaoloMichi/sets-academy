package it.sets.resource.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.sets.resource.model.Stanza;

@Repository
public interface StanzaRepository extends JpaRepository<Stanza, Long> {
	
	public List<Stanza> findAll();
	
	public Optional<Stanza> findById(Long id);
	
	public Stanza save(Stanza stanza);
	
	public void deleteById(Long id);

}
