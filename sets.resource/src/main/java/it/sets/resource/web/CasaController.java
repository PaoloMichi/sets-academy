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

import it.sets.resource.model.Casa;
import it.sets.resource.model.Propietario;
import it.sets.resource.service.CasaService;

@RestController
@RequestMapping(CasaController.URI_SPEC)
public class CasaController {
	
	@Autowired
	CasaService casaService;

	protected static final String URI_SPEC = "/casa";
	
	@GetMapping(value = "")
	public List<Casa> findAll() {
		return casaService.findAll();
	}

	@GetMapping(value = "/{id}")
	public Casa findyId(@PathVariable Long id) {
		return casaService.findById(id);
	}

	@PostMapping(value = "")
	public Casa save(@RequestBody Casa casa) {
		casa.setId(null);
		return casaService.save(casa);
	}

	@PutMapping(value = "")
	public Casa update(@RequestBody Casa casa) {
		
		return casaService.save(casa);
	}

	@DeleteMapping(value = "/{id}")
	public String delete(@PathVariable Long id) {
		casaService.deleteById(id);
		return "OK";
	}
	
	@GetMapping (value = "/casawithaffit/{conaffit}")
	public List<Casa> findWithorWithoutAffit (@PathVariable boolean conaffit) {
		
		return casaService.findWithorWithoutAffit(conaffit);
		
	}
	
	@PutMapping (value = "/removeaffit/{id}")
	public Casa removeAffitFromCasa (@PathVariable Long id){
		
		return casaService.removeAffitFromCase(id);
	}
}
