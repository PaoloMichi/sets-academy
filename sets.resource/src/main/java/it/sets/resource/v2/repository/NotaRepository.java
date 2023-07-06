package it.sets.resource.v2.repository;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.sets.resource.v2.model.Categoria;
import it.sets.resource.v2.model.Nota;
import it.sets.resource.v2.model.Priority;



@Repository
public interface NotaRepository extends JpaRepository<Nota, Long>{
	
//	//scrivere così le crud
	
	
	public List<Nota> findByCheckedIsTrue(); //nome personalizzato
	
	public List<Nota> findByCheckedIsFalse();

	public List<Nota> findByExpireDateBefore(Date todayDate);
	
	@Query(value = "SELECT * FROM Nota n", nativeQuery = true)
	List<Nota> findAllCustom();
	
	//SELECT c.* FROM sets.categoria c
	//	INNER JOIN sets.nota n ON n.categoria_id = c.id
	//	WHERE n.id = 29 questa sarebbe la query nativa che andiamo a sostituire con il rigo 41
	
	@Query(value = "SELECT n.categoria FROM Nota n WHERE n.id = :noteId") 
	Categoria findCategoryByNoteId(@Param("noteId")Long id); //creare chiamata controller service

	@Query(value = "SELECT * FROM Nota n WHERE n.id = :noteId", nativeQuery = true)
	Optional<Nota> findByIdCustom(@Param("noteId")Long id);

//	@Query(value = "SELECT n FROM Nota n WHERE n.checked = true")
//	List<Nota> findByCheckedIsTrueCustom();

	@Query(value = "SELECT * FROM Nota n WHERE n.checked = :checked", nativeQuery = true)
	List<Nota> findByIfCheckedCustom(@Param("checked") Boolean checked);

	@Query(value = "SELECT * FROM sets.nota n WHERE n.expire_date < :todayDate", nativeQuery = true)
	List<Nota> findByExpireDateBeforeCustom(@Param("todayDate") Date todayDate);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM Nota WHERE id = :noteId", nativeQuery = true)
	void deleteByIdCustom(@Param("noteId") Long id); //sistemare param
//	@Transactional ripristina i dati se una modifica non va a buon fine, ad esempio se si modificano dati in due table

}
