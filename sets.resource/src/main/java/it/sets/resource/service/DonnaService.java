package it.sets.resource.service;

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

	public Donna save(Donna donna) {
		
		return donnaRepository.save(donna);
	}

	public void deleteById(Long id) {
		Donna donna = donnaRepository.findById(id).get();
		donnaRepository.delete(donna);
	}
}
