package it.sets.resource.v2.web;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

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

import it.sets.resource.response.ResponseBase;
import it.sets.resource.v2.model.Nota;
import it.sets.resource.v2.service.NotaService;
import it.sets.resource.v2.web.dto.NotaDtoGet;
import it.sets.resource.v2.web.dto.NotaDtoPost;
import it.sets.resource.v2.web.dto.mapper.NotaMapperDtoGet;
import it.sets.resource.v2.web.dto.mapper.NotaMapperDtoPost;

@RestController
@RequestMapping(NotaController.URI_SPEC)
public class NotaController {

	@Autowired
	NotaService notaService;
	
	protected static final String URI_SPEC = "/nota";
	
	@GetMapping(value = "")
	public List<Nota> findAll(){
		return notaService.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public ResponseBase<NotaDtoGet> findById(@PathVariable Long id) throws Exception{
		ResponseBase<NotaDtoGet> response = new ResponseBase<NotaDtoGet>();
		NotaDtoGet notaDto = NotaMapperDtoGet.toDtoGet(notaService.findById(id));
		
		try {
			response.setResponse(notaDto);
			response.setCode(200);
			response.setMessage("OK");
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
			response.setCode(500);
			response.setMessage("KO");
		
		}
		return response;
	}
	
	@GetMapping(value = "/findbycheck")
	public ResponseBase<List<Nota>> findByCheck(@RequestParam Boolean check) {
		
		ResponseBase<List<Nota>> response = new ResponseBase<List<Nota>>();
		
		try {
			response.setResponse(notaService.findByIsCheck(check));
			response.setCode(200);
			response.setMessage("OK");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			response.setCode(500);
			response.setMessage("KO");
		}
		
		return response;
		
	}
	
	@GetMapping(value = "/expired")
	public ResponseBase<List<Nota>> findExpiredList(Date todayDate) {
		todayDate = new Date();
		ResponseBase<List<Nota>> response = new ResponseBase<List<Nota>>();
		try {
			response.setResponse(notaService.findByExpiredList(todayDate));
			response.setCode(200);
			response.setMessage("OK");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			response.setCode(500);
			response.setMessage("KO");
		}
		
		return response;
	}
	
	@PostMapping(value = "/add")
	public ResponseBase<NotaDtoPost> addNota(@RequestBody NotaDtoPost dto) {
		ResponseBase<NotaDtoPost> response = new ResponseBase<NotaDtoPost>();

		try {
			Nota notaEntity = NotaMapperDtoPost.toEntity(dto);
			NotaDtoPost notaDto = NotaMapperDtoPost.toDtoPost(notaService.addNota(notaEntity));
			response.setResponse(notaDto);
			response.setCode(200);
			response.setMessage("OK");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			response.setCode(500);
			response.setMessage("KO");
		}
		
		return response;
	}

	@PutMapping(value = "/update")
	public ResponseBase<NotaDtoPost> updateNota(@RequestBody NotaDtoPost dto) {
		ResponseBase<NotaDtoPost> response = new ResponseBase<NotaDtoPost>();
		
		try {
			Nota notaEntity = NotaMapperDtoPost.toEntity(dto);
			NotaDtoPost notaDtoPost = NotaMapperDtoPost.toDtoPost(notaService.updateNota(notaEntity));
			response.setResponse(notaDtoPost);
			response.setCode(200);
			response.setMessage("OK");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			response.setCode(500);
			response.setMessage("KO");
		}
		return response;
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseBase<Long> delete(@PathVariable Long id) {
		
		ResponseBase<Long> response = new ResponseBase<Long>();

		try {
			notaService.deleteById(id);
			response.setCode(204);
			response.setMessage("NO_CONTENT");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			response.setCode(500);
			response.setMessage("KO");
		}
		
		return response;
	}
}
