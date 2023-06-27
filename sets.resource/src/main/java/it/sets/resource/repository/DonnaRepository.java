package it.sets.resource.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.sets.resource.model.Donna;

@Repository
public interface DonnaRepository extends JpaRepository<Donna, Long> {
public List<Donna> findAll();
	
	public Optional<Donna> findById(Long id);
	
	public Donna save(Donna donna);
	
	public void deleteById(Long id);

	public Long findByIdIsNull();
	
//	@Query(value = "SELECT * FROM COUPLE", nativeQuery = true)
//		List<Object> findCouples();
}
