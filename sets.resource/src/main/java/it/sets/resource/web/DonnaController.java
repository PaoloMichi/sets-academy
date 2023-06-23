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

import it.sets.resource.model.Donna;
import it.sets.resource.service.DonnaService;

@RestController
@RequestMapping(DonnaController.URI_SPEC)
public class DonnaController {
	
	@Autowired
	DonnaService donnaService;
	
	protected static final String URI_SPEC = "/donna";
	
	@GetMapping(value = "")
	public List<Donna> findAll() {
		return donnaService.findAll();
	}

	@GetMapping(value = "/{id}")
	public Donna findyId(@PathVariable Long id) {
		return donnaService.findById(id);
	}

	@PostMapping(value = "")
	public Donna save(@RequestBody Donna donna) {
		donna.setId(null);
		return donnaService.save(donna);
	}

	@PutMapping(value = "")
	public Donna update(@RequestBody Donna donna) {
		
		return donnaService.save(donna);
	}

	@DeleteMapping(value = "/{id}")
	public String delete(@PathVariable Long id) {
		donnaService.deleteById(id);
		return "OK";
	}
}
