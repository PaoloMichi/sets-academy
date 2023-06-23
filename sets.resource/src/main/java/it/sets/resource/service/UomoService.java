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

	public Uomo save(Uomo uomo) {
		
		return uomoRepository.save(uomo);
	}

	public void deleteById(Long id) {
		Uomo uomo = uomoRepository.findById(id).get();
		uomoRepository.delete(uomo);
	}
}
