package it.sets.resource.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.sets.resource.model.Stanza;
import it.sets.resource.service.StanzaService;




@RestController
@RequestMapping(StanzaController.URI_SPEC)
public class StanzaController {
	
	@Autowired
	StanzaService stanzaService;
	
	protected static final String URI_SPEC = "/stanza";
	
	@GetMapping(value = "")
	public List<Stanza> findAll() {
		return stanzaService.findAll();
	}

	@GetMapping(value = "/{id}")
	public Stanza findyId(@PathVariable Long id) {
		return stanzaService.findById(id);
	}

	@PostMapping(value = "")
	public Stanza save(@RequestBody Stanza stanza) {
		stanza.setId(null);
		return stanzaService.save(stanza);
	}

	@PutMapping(value = "")
	public Stanza update(@RequestBody Stanza stanza) {
		stanza.getDescription();
		return stanzaService.save(stanza);
	}

	@DeleteMapping(value = "/{id}")
	public String delete(@PathVariable Long id) {
		stanzaService.deleteById(id);
		return "OK";
	}
}
