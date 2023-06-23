package it.sets.resource.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.sets.resource.model.Casa;
import it.sets.resource.model.Propietario;
import it.sets.resource.repository.CasaRepository;
import it.sets.resource.repository.PropietarioRepository;

@Service
public class PropietarioService {
	
	@Autowired
	PropietarioRepository propietarioRepository;
	
	@Autowired
	CasaRepository casaRepository;
	
	public List<Propietario> findAll(){
		return propietarioRepository.findAll();
	}
	
	public Propietario findById(Long id){
		Propietario propietario = propietarioRepository.findById(id).get();
		return propietario;
	}
	
	public Propietario save(Propietario propietario) {
		
		return propietarioRepository.save(propietario);
	}
	
	
	public void deleteById(Long id) {
		Propietario propietario = propietarioRepository.findById(id).get();
		propietarioRepository.delete(propietario);
	}

	public List<Propietario> findWithorWithoutCasa(boolean concasa) {
		if (concasa) {
			return propietarioRepository.findByCasesIsNotNull();
		}
		return propietarioRepository.findByCasesIsNull();
	}

	public Propietario removeCaseFromPropietario(Long id) {
		
		
		Propietario propietario = propietarioRepository.findById(id).get();
	
		propietario.setCases(new ArrayList<Casa>());
		
		return propietarioRepository.save(propietario);
	}

	public Propietario saveOnePropietarioAndHisLists(Propietario propietario) {
		
		propietarioRepository.save(propietario);
		
			List<Casa> casas = propietario.getCases();
			for (Casa casa : casas) {
				casa.setPropietario(propietario);
				casaRepository.save(casa);
				
			}
		return propietario;
	}
	
	public List<Propietario> savePropietariAndTheirLists(List<Propietario> propietari) {
		
		List<Propietario> result = new ArrayList<Propietario>();
		
		for (Propietario propietario : propietari) { 
			
			result.add(saveOnePropietarioAndHisLists(propietario));
		}
		
		return result;
	}
}
