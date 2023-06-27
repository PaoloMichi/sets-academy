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
import it.sets.resource.model.Donna;
import it.sets.resource.response.ResponseBase;
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
	public ResponseBase<Donna> save(@RequestBody Donna donna) {
		ResponseBase<Donna> response = new ResponseBase<Donna>();
		try {
			response.setResponse(donnaService.save(donna));
			response.setCode(200);
			response.setMessage("OK");
		} catch (Exception e) {
			response.setCode(500);
			response.setMessage("KO");
		}
		return response;
	}

	
	@PutMapping(value = "")
	public ResponseBase<Donna> updateDonna(@RequestBody Donna donna) {
		
		ResponseBase<Donna> response = new ResponseBase<Donna>();
		try {
			response.setResponse(donnaService.updateDonna(donna));
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
			donnaService.deleteById(id);
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
