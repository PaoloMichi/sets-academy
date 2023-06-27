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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.sets.common.model.web.BaseResponse;
import it.sets.resource.model.Couple;
import it.sets.resource.response.ResponseBase;
import it.sets.resource.service.CoupleService;

@RestController
@RequestMapping(CoupleController.URI_SPEC)
public class CoupleController {
	
	@Autowired
	CoupleService coupleService;
	
	protected static final String URI_SPEC = "/couple";
	
	@GetMapping(value = "")
	public ResponseBase<List<Couple>> findAll() { 
		
		ResponseBase<List<Couple>> response = new ResponseBase<List<Couple>>();
		
		try{
			response.setResponse(coupleService.findAll());
			response.setCode(200);
			response.setMessage("OK");
		} catch (Exception e){
			response.setCode(500);
			response.setMessage("KO");
		}
		
		return response;
	}

	@GetMapping(value = "/{id}")
	public ResponseBase<Couple> findyId(@PathVariable Long id) {
		
		ResponseBase<Couple> response = new ResponseBase<Couple>();
		response.setResponse(coupleService.findById(id));
		
		if (response.getResponse() != null) {
			response.setCode(200);
			response.setMessage("ok");
		}else {
			response.setCode(500);
			response.setMessage("ko");
		}
		return response;
	}

	@PostMapping(value = "/addcouple")
	public ResponseBase<Couple> addCouple(@RequestParam Long idDonna,
						    @RequestParam Long idUomo) {
		
		ResponseBase<Couple> response = new ResponseBase<Couple>();
		try {
			response.setResponse(coupleService.addCouple(idDonna, idUomo));
			response.setCode(200);
			response.setMessage("OK");
		} catch (Exception e) {
			response.setCode(500);
			response.setMessage("KO");
		}
		
		return response;
	}

	@PutMapping(value = "/updatecouple")
	public ResponseBase<Couple> updateCouple(@RequestBody Couple couple) {
		
		ResponseBase<Couple> response = new ResponseBase<Couple>();
		try {
			response.setResponse(coupleService.updateCouple(couple));
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
			coupleService.deleteById(id);
			response.setCode(204);
			response.setMessage("NO_CONTENT");
		} catch (Exception e) {
			response.setCode(500);
			response.setMessage("KO");
		}
		
		return response;
	}
}
