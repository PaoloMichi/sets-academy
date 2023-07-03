package it.sets.resource.v2.repository;


import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.sets.resource.v2.model.Nota;



@Repository
public interface NotaRepository extends JpaRepository<Nota, Long>{
	
	public List<Nota> findByCheckedIsTrue();
	
	public List<Nota> findByCheckedIsFalse();

	public List<Nota> findByExpireDateBefore(Date todayDate);
	
}
