package it.sets.resource.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.sets.resource.model.Stanza;
import it.sets.resource.repository.StanzaRepository;

@Service
public class StanzaService {
	
	@Autowired
	StanzaRepository stanzaRepository;
	
	
	public List<Stanza> findAll() {
		return stanzaRepository.findAll();
	}

	public Stanza findById(Long id) {
		Stanza stanza = stanzaRepository.findById(id).get();
		return stanza;
	}

	public Stanza save(Stanza stanza) {
		
		return stanzaRepository.save(stanza);
	}

	public void deleteById(Long id) {
		Stanza stanza = stanzaRepository.findById(id).get();
		stanzaRepository.delete(stanza);
	}
}
