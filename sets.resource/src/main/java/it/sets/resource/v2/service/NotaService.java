package it.sets.resource.v2.service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.net.httpserver.Authenticator.Result;

import it.sets.resource.v2.model.Nota;
import it.sets.resource.v2.repository.NotaRepository;

@Service
public class NotaService {

	@Autowired
	NotaRepository notaRepository;
	
	
	
	public List<Nota> findAll() {
		
		return notaRepository.findAll();
	}

	public Nota findById(Long id) throws Exception{
	    Nota nota = new Nota();
		if (notaRepository.existsById(id)) {
			nota = notaRepository.findById(id).get();
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
	
	public List<Nota> findByExpiredList(Date todayDate) {

		return	notaRepository.findByExpireDateBefore(todayDate);
		
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


	public Nota updateNota(Nota notaEntity) throws Exception {
		
		Nota toUpdateNote = notaRepository.findById((long) notaEntity.getId()).get();
		
		if (notaEntity.getId() != null && notaEntity.getExpireDate().after(new Date())) {
			notaEntity.setCreationDate(toUpdateNote.getCreationDate());
			notaEntity.setLastModifyDate(new Date());
			notaRepository.save(notaEntity);
		} else {
			throw new Exception("id non esistente o data scadenza non valida");
		}
		return notaEntity;
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

}
