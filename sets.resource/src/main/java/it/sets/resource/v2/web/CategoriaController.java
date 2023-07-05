package it.sets.resource.v2.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import it.sets.resource.response.ResponseBase;
import it.sets.resource.v2.model.Categoria;
import it.sets.resource.v2.service.CategoriaService;

@RestController
@RequestMapping(CategoriaController.URI_SPEC)
public class CategoriaController {

	@Autowired
	CategoriaService categoriaService;
	
	protected static final String URI_SPEC = "/categoria";
	
	@GetMapping(value = "")
	public ResponseBase<List<Categoria>> findAll() {
		ResponseBase<List<Categoria>> response = new ResponseBase<List<Categoria>>();
		
		try {
			response.setResponse(categoriaService.findAll());
			response.setCode(200);
			response.setMessage("OK");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			response.setCode(500);
			response.setMessage("KO");
		}
		
		return response;
	}
	
	@GetMapping(value = "/{id}")
	public ResponseBase<Categoria> findById(@PathVariable Long id) {
		ResponseBase<Categoria> response = new ResponseBase<Categoria>();
		
		try {
			response.setResponse(categoriaService.findbyId(id));
			response.setCode(200);
			response.setMessage("OK");
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			response.setCode(500);
			response.setMessage("KO");
		}
		
		return response;
	}
	
	@PostMapping(value = "")
	public ResponseBase<Categoria> addCategoria(@RequestBody Categoria categoria) {
		ResponseBase<Categoria> response = new ResponseBase<Categoria>();
		
		try {
			response.setResponse(categoriaService.addCategoria(categoria));
			response.setCode(200);
			response.setMessage("OK");
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			response.setCode(500);
			response.setMessage("KO");
		}
		
		return response;
	}
	
	@PutMapping(value = "/{id}")
	public ResponseBase<Categoria> UpdateCategoria(@RequestBody Categoria categoria,
												   @PathVariable Long id) {
		ResponseBase<Categoria> response = new ResponseBase<Categoria>();
		
		try {
			response.setResponse(categoriaService.updateCategoria(categoria, id));
			response.setCode(200);
			response.setMessage("OK");
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			response.setCode(500);
			response.setMessage("KO");
		}
		
		return response;
	}
}
