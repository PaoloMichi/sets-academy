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

import it.sets.resource.model.Uomo;
import it.sets.resource.model.Uomo;
import it.sets.resource.response.ResponseBase;
import it.sets.resource.service.UomoService;

@RestController
@RequestMapping(UomoController.URI_SPEC)
public class UomoController {
	
	@Autowired
	UomoService uomoService;
	
	protected static final String URI_SPEC = "/uomo";
	
	@GetMapping(value = "")
	public List<Uomo> findAll() {
		return uomoService.findAll();
	}

	@GetMapping(value = "/{id}")
	public Uomo findyId(@PathVariable Long id) {
		return uomoService.findById(id);
	}

	@PostMapping(value = "")
	public ResponseBase<Uomo> save(@RequestBody Uomo uomo) {
		ResponseBase<Uomo> response = new ResponseBase<Uomo>();
		try {
			response.setResponse(uomoService.save(uomo));
			response.setCode(200);
			response.setMessage("OK");
		} catch (Exception e) {
			response.setCode(500);
			response.setMessage("KO");
		}
		return response;
	}

	
	@PutMapping(value = "")
	public ResponseBase<Uomo> updateUomo(@RequestBody Uomo uomo) {
		
		ResponseBase<Uomo> response = new ResponseBase<Uomo>();
		try {
			response.setResponse(uomoService.updateUomo(uomo));
			response.setCode(200);
			response.setMessage("OK");
		} catch (Exception e) {
			response.setCode(500);
			response.setMessage("KO");
		}
		
		return response;
	}
	 
	
	@DeleteMapping(value = "/{id}")
	public ResponseBase<Long> delete(@PathVariable Long id) {
		
		ResponseBase<Long> response = new ResponseBase<Long>();
		
		try {
			uomoService.deleteById(id);
			response.setResponse(id);
			response.setCode(200);
			response.setMessage("OK");
		} catch (Exception e) {
			response.setResponse(null);
			response.setCode(500);
			response.setMessage("KO");
		}
		
		return response;
	}
}
