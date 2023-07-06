package it.sets.resource.v2.service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.net.httpserver.Authenticator.Result;

import it.sets.resource.v2.model.Categoria;
import it.sets.resource.v2.model.Nota;
import it.sets.resource.v2.repository.CategoriaRepository;
import it.sets.resource.v2.repository.NotaRepository;

@Service
public class NotaService {

	@Autowired
	NotaRepository notaRepository;
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	public List<Nota> findAll() {
		
		return notaRepository.findAll();
	}

	public List<Nota> findAllCustom() {
		
		return notaRepository.findAllCustom();
	}
	
	public Nota findById(Long id) throws NoSuchElementException{
	    Nota nota = new Nota();
		if (notaRepository.existsById(id)) {
			nota = notaRepository.findById(id).get();
		} else {
			throw new NoSuchElementException("id non esistente");
		} 
		return nota;
	}
	
	public Nota findByIdCustom(Long id) throws NoSuchElementException{
	    Nota nota = new Nota();
		if (notaRepository.existsById(id)) {
			nota = notaRepository.findByIdCustom(id).get();
		} else {
			throw new NoSuchElementException("id non esistente");
		} 
		return nota;
	}
	
	public List<Nota> findByIsCheck(Boolean check) throws Exception {
		
		List<Nota> result = null;
		
		if (!check) {
			result = notaRepository.findByCheckedIsFalse();
		}else if (check){
			result = notaRepository.findByCheckedIsTrue();
		} 
//			else {
//			throw new Exception("Valori accettati: vero o falso");
//		}
		return result;
	}
	
	public List<Nota> findByIsCheckCustom(Boolean check) throws Exception {
		
		List<Nota> result = null;
		
		if (!check) {
			result = notaRepository.findByIfCheckedCustom(check);
		}else if (check){
			result = notaRepository.findByIfCheckedCustom(check);
		} 
//			else {
//			throw new Exception("Valori accettati: vero o falso");
//		}
		return result;
	}
	
	public List<Nota> findByExpiredList(Date todayDate) {

		return	notaRepository.findByExpireDateBefore(todayDate);
		
	}
	
	public List<Nota> findByExpiredListCustom() {
		
		return	notaRepository.findByExpireDateBeforeCustom(new Date());
		
	}
	
	public Nota addNota(Nota notaEntity) throws Exception {
		notaEntity.setId(null);
		notaEntity.setCreationDate(new Date());
		notaEntity.setLastModifyDate(new Date());
		notaEntity.setChecked(false);
		//		Calendar calendar = Calendar.getInstance();
		//	    calendar.setTime(notaEntity.getCreationDate());
		//	    calendar.add(Calendar.MONTH, 6);
		//	    notaEntity.setExpireDate(calendar.getTime());

//		if (notaEntity.getExpireDate().before(notaEntity.getCreationDate())) {
//			throw new Exception("Data scadenza non valida");		 
//		}
		return notaRepository.save(notaEntity);
	}

//	public Nota updateNota(Nota notaEntity, Long id) throws Exception {
//		
//		if (notaEntity.getId() != null && notaEntity.getExpireDate().after(new Date())) {
//			notaRepository.findById(id).get();
//			notaEntity.setCreationDate(toUpdateNote.getCreationDate());
//			notaEntity.setLastModifyDate(new Date());
//			notaRepository.save(notaEntity);
//		} else {
//			throw new Exception("id non esistente o data scadenza non valida");
//		}
//		return notaEntity;
//	}
	
	public Nota updateNotaOnlyTitleAndDescr(Nota notaEntity, Long id) throws Exception {
		
		if (notaRepository.existsById(id)) {
			Nota toUpdateNote = notaRepository.findById(id).get();
			toUpdateNote.setDescription(notaEntity.getDescription());
			toUpdateNote.setTitle(notaEntity.getTitle());
			notaRepository.save(notaEntity);
		} else {
			throw new Exception("id non esistente");
		}
		return notaEntity;
	}
	
	public Nota updateNotaAll(Nota notaEntity) throws Exception {
	
		return notaRepository.save(notaEntity);
	}

	public void deleteById(Long id) throws Exception {
		Nota nota = notaRepository.findById(id).get();
		if (nota.getChecked()) {
			notaRepository.delete(nota);
		}
		else {
			throw new Exception("checked is not true");
		}
	}

	public void deleteByIdCustom(Long id) throws Exception {
		Nota nota = notaRepository.findById(id).get();
		if (nota.getChecked()) {
			notaRepository.deleteByIdCustom(id);
		}
		else {
			throw new Exception("checked is not true");
		}
	}
	
	public Nota toChangeChecked(Long id, Boolean checked) throws Exception {
		Nota toUpdateNote = notaRepository.findById(id).get();
		
		if (notaRepository.existsById(id)) {
			toUpdateNote.setChecked(checked);
			notaRepository.save(toUpdateNote);
		} else {
			throw new Exception("id non esistente");
		}
		return toUpdateNote;
	}

	public Nota addCategoria(Long idNota, Long idCategoria) {
		Nota toUpdate = null;
		if (notaRepository.existsById(idNota) && categoriaRepository.existsById(idCategoria)) {
			Categoria categoria = categoriaRepository.findById(idCategoria).get();
			toUpdate = notaRepository.findById(idNota).get();
			toUpdate.setCategoria(categoria);
		} else {
			throw new NoSuchElementException();
		}
		return notaRepository.save(toUpdate);
	}

	public Categoria findCategoryByNoteId(Long idNota) {
		
		return notaRepository.findCategoryByNoteId(idNota);
	}

}