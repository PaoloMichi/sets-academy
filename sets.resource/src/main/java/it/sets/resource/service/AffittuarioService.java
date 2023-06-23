package it.sets.resource.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.sets.resource.model.Affittuario;
import it.sets.resource.repository.AffittuarioRepository;

@Service
public class AffittuarioService {
	
	@Autowired
	AffittuarioRepository affittuarioRepository;
	
	
	public List<Affittuario> findAll(){
		return affittuarioRepository.findAll();
	}
	
	public Affittuario findById(Long id){
		Affittuario affittuario = affittuarioRepository.findById(id).get();
		return affittuario;
	}
	
	public Affittuario save(Affittuario affittuario) {
		
		return affittuarioRepository.save(affittuario);
	}
	
	
	public void deleteById(Long id) {
		Affittuario affittuario = affittuarioRepository.findById(id).get();
		affittuarioRepository.delete(affittuario);
	}

	public List<Affittuario> findWithorWithoutAffit(boolean concasa) {
		if (concasa) {
			return affittuarioRepository.findByCasa_IdIsNotNull();
		}
		return affittuarioRepository.findByCasa_IdIsNull();
	}
}
