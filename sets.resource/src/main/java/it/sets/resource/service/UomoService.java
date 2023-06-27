package it.sets.resource.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.sets.resource.model.Uomo;
import it.sets.resource.repository.UomoRepository;

@Service
public class UomoService {
	@Autowired
	UomoRepository uomoRepository;
	
	
	public List<Uomo> findAll() {
		return uomoRepository.findAll();
	}

	public Uomo findById(Long id) {
		Uomo uomo = uomoRepository.findById(id).get();
		return uomo;
	}

	public Uomo save(Uomo uomo) throws Exception {
		Uomo result = null;
		if (uomo.getId() != null) {
			throw new Exception("l'id non va inserito, si autoincrementa");
		}
		result = uomoRepository.save(uomo);
		return result;
	}

	public void deleteById(Long id) throws Exception {
		
		Uomo uomo = uomoRepository.findById(id).get();
		uomoRepository.delete(uomo);
	}

	public Uomo updateUomo(Uomo uomo) throws Exception {
		Uomo result = null;
		if (uomoRepository.existsById(uomo.getId())) {	
			result = uomoRepository.save(uomo);
			return result;
			} 		
		throw new Exception("Condizioni per la coppia non idonee");
	}
}
