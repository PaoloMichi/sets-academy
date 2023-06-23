package it.sets.resource.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.sets.resource.model.Propietario;
import it.sets.resource.service.PropietarioService;

@RestController
@RequestMapping(PropietarioController.URI_SPEC)
public class PropietarioController {
	@Autowired
	PropietarioService propietarioService;

	protected static final String URI_SPEC = "/propietario";
	
	@GetMapping(value = "")
	public List<Propietario> findAll() {
		return propietarioService.findAll();
	}

	@GetMapping(value = "/{id}")
	public Propietario findyId(@PathVariable Long id) {
		return propietarioService.findById(id);
	}

	@PostMapping(value = "")
	public Propietario save(@RequestBody Propietario propietario) {
		propietario.setId(null);
		return propietarioService.save(propietario);
	}

	@PutMapping(value = "")
	public Propietario update(@RequestBody Propietario propietario) {
		
		return propietarioService.save(propietario);
	}

	@DeleteMapping(value = "/{id}")
	public String delete(@PathVariable Long id) {
		propietarioService.deleteById(id);
		return "OK";
	}
	
	@GetMapping (value = "/ownerwithcasa/{concasa}")
	public List<Propietario> findWithorWithoutCasa (@PathVariable boolean concasa) {
		
		return propietarioService.findWithorWithoutCasa(concasa);
		
	}
	
	
	@PutMapping (value = "/removecasa/{id}")
	public Propietario removeCaseFromPropietario (@PathVariable Long id){
		
		return propietarioService.removeCaseFromPropietario(id);
	}
	
	@PostMapping (value = "/savelistsone")
	public Propietario savePropietarioAndHisLists(@RequestBody Propietario propietario) {
		
		return propietarioService.saveOnePropietarioAndHisLists(propietario);
	}
	
	@PostMapping (value = "/savelists")
	public List<Propietario> savePropietariAndTheirLists(@RequestBody List<Propietario> propietari) {
		
		return propietarioService.savePropietariAndTheirLists(propietari);
	}
	
}
