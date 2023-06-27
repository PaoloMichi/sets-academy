package it.sets.resource.service;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.sets.resource.model.Couple;
import it.sets.resource.model.Donna;
import it.sets.resource.model.Uomo;
import it.sets.resource.repository.CoupleRepository;
import it.sets.resource.repository.DonnaRepository;
import it.sets.resource.repository.UomoRepository;

@Service
public class CoupleService {
	@Autowired
	CoupleRepository coupleRepository;

	@Autowired
	DonnaRepository donnaRepository;

	@Autowired
	UomoRepository uomoRepository;

	public List<Couple> findAll() {
		return coupleRepository.findAll();
	}

	public Couple findById(Long id) {
		
		Couple couple = new Couple();
		
		try {
			if (coupleRepository.existsById(id)) {
				
				couple = coupleRepository.findById(id).get();
			}else {

				couple.setId(null);
				couple.setDonna(null);
				couple.setUomo(null);
			}
		} catch (NoSuchElementException e) {
			
			return null;
		}
		
		return couple;
	}

	/*
	 * public Couple addCouple(Long idDonna, Long idUomo) throws Exception {
	 * 
	 * Couple couple = new Couple();
	 * 
	 * Donna donna = new Donna(); Uomo uomo = new Uomo();
	 * 
	 * 
	 * if (donnaRepository.existsById(idDonna) && uomoRepository.existsById(idUomo))
	 * {
	 * 
	 * donna = donnaRepository.findById(idDonna).get(); uomo =
	 * uomoRepository.findById(idUomo).get();
	 * 
	 * 
	 * Integer differenzaEta = Math.abs(donna.getEta() - uomo.getEta());
	 * 
	 * if (differenzaEta >= 10) {
	 * 
	 * throw new Exception("condizioni non idonee"); }
	 * 
	 * 
	 * } else {
	 * 
	 * couple.setId(null); couple.setDonna(donna); couple.setUomo(uomo); }
	 * 
	 * 
	 * 
	 * return coupleRepository.save(couple); }
	 */
	
	public Couple addCouple(Long idDonna, Long idUomo) throws Exception {

		Couple result = null;
		
			if (donnaRepository.existsById(idDonna) && uomoRepository.existsById(idUomo)) {
				Donna donna = donnaRepository.findById(idDonna).get();
				Uomo uomo = uomoRepository.findById(idUomo).get();
				Integer differenzaEta = Math.abs(donna.getEta() - uomo.getEta());
				if (differenzaEta >= 10 || donna.getEta() < uomo.getEta()) {
					throw new Exception("Condizioni per la coppia non idonee");
				}
				Couple couple = new Couple();
				couple.setUomo(uomo);
				couple.setDonna(donna);
				result = coupleRepository.save(couple);
			} else {
				throw new Exception("Almeno uno dei due id non corrisponde a un dato presente nel DB");
			}
		return result;
	}

	public Couple updateCouple(Couple couple) throws Exception {
		
		Couple result = null;
		if (couple.getId() != null) {	
			Integer differenzaEta = Math.abs(couple.getDonna().getEta() - couple.getUomo().getEta());
			if (differenzaEta >= 10 && couple.getDonna().getEta() < couple.getUomo().getEta()) {
				throw new Exception("Condizioni per la coppia non idonee");
			} 
			result = coupleRepository.save(couple);		
		}else {
			throw new Exception("id non esistente");
		}
		return result;
	}

	public void deleteById(Long id) throws Exception {
		Couple couple = coupleRepository.findById(id).get();
		coupleRepository.delete(couple);
	}
}
