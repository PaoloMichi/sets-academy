package it.sets.resource.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.sets.resource.model.Donna;
import it.sets.resource.repository.DonnaRepository;

@Service
public class DonnaService {
	@Autowired
	DonnaRepository donnaRepository;
	
	
	public List<Donna> findAll() {
		return donnaRepository.findAll();
	}

	public Donna findById(Long id) {
		Donna donna = donnaRepository.findById(id).get();
		return donna;
	}

	public Donna save(Donna donna) throws Exception {
		Donna result = null;
		if (donna.getId() != null) {
			throw new Exception("l'id non va inserito, si autoincrementa");
		}
		result = donnaRepository.save(donna);
		return result;
	}

	public void deleteById(Long id) throws Exception {
		
		Donna donna = donnaRepository.findById(id).get();
		donnaRepository.delete(donna);
	}

	public Donna updateDonna(Donna donna) throws Exception {
		if (!donnaRepository.existsById(donna.getId())) {	
			throw new Exception("Condizioni per la coppia non idonee");
		} 		
		return donnaRepository.save(donna);
	}
}
