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

import it.sets.resource.model.Affittuario;
import it.sets.resource.model.Casa;
import it.sets.resource.service.AffittuarioService;

@RestController
@RequestMapping(AffittuarioController.URI_SPEC)
public class AffittuarioController {
	@Autowired
	AffittuarioService affittuarioService;

	protected static final String URI_SPEC = "/affittuario";
	
	@GetMapping(value = "")
	public List<Affittuario> findAll() {
		return affittuarioService.findAll();
	}

	@GetMapping(value = "/{id}")
	public Affittuario findyId(@PathVariable Long id) {
		return affittuarioService.findById(id);
	}

	@PostMapping(value = "")
	public Affittuario save(@RequestBody Affittuario affittuario) {
		affittuario.setId(null);
		return affittuarioService.save(affittuario);
	}

	@PutMapping(value = "")
	public Affittuario update(@RequestBody Affittuario affittuario) {
		
		return affittuarioService.save(affittuario);
	}

	@DeleteMapping(value = "/{id}")
	public String delete(@PathVariable Long id) {
		affittuarioService.deleteById(id);
		return "OK";
	}
	
	@GetMapping (value = "/affitwithcasa/{concasa}")
	public List<Affittuario> findWithorWithoutCasa (@PathVariable boolean concasa) {
		
		return affittuarioService.findWithorWithoutAffit(concasa);
		
	}
}
