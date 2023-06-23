package it.sets.resource.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.sets.resource.model.Casa;
import it.sets.resource.model.Propietario;
import it.sets.resource.repository.CasaRepository;

@Service
public class CasaService {
	
	@Autowired
	CasaRepository casaRepository;
	
	public List<Casa> findAll(){
		return casaRepository.findAll();
	}
	
	public Casa findById(Long id){
		Casa casa = casaRepository.findById(id).get();
		return casa;
	}
	
	public Casa save(Casa casa) {
		
		return casaRepository.save(casa);
	}
	
	
	public void deleteById(Long id) {
		Casa casa = casaRepository.findById(id).get();
		casaRepository.delete(casa);
	}

	public List<Casa> findWithorWithoutAffit(boolean conaffit) {
		if (conaffit) {
			return casaRepository.findByAffittuariIsNotNull();
		}
		return casaRepository.findByAffittuariIsNull();
	}

	public Casa removeAffitFromCase(Long id) {
		
		Casa casa = casaRepository.findById(id).get();
		
		casa.setAffittuari(null);
		
		return casaRepository.save(casa);
	}
}
