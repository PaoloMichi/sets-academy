package it.sets.resource.v2.web;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sun.xml.bind.v2.TODO;

import it.sets.resource.response.ResponseBase;
import it.sets.resource.v2.model.Categoria;
import it.sets.resource.v2.model.Nota;
import it.sets.resource.v2.service.NotaService;
import it.sets.resource.v2.web.dto.NotaDtoGet;
import it.sets.resource.v2.web.dto.NotaDtoPost;
import it.sets.resource.v2.web.dto.mapper.NotaMapperDtoGet;
import it.sets.resource.v2.web.dto.mapper.NotaMapperDtoPost;

@RestController
@CrossOrigin(origins = "http://localhost:4200")//guardare come configurare nelle propierties
@RequestMapping(NotaController.URI_SPEC)
public class NotaController {

	@Autowired
	NotaService notaService;
	
	protected static final String URI_SPEC = "/nota";
	
	//TODO fare con response base
//	@GetMapping(value = "")
//	public ResponseBase<List<Nota>> findAll(){
//		
//		ResponseBase<List<Nota>> response = new ResponseBase<List<Nota>>();
//		
//		try {
//			response.setResponse(notaService.findAll());
//			response.setCode(200);
//			response.setMessage("OK");
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//			response.setCode(500);
//			response.setMessage("KO");
//		}
//		
//		return response;
//	}
	
	@GetMapping(value = "")
	public List<Nota> findAll(){
		
		return notaService.findAll();
	}
	
	//TODO fare senza dto
//	@GetMapping(value = "/{id}")
//	public ResponseBase<NotaDtoGet> findById(@PathVariable Long id) {
//		ResponseBase<NotaDtoGet> response = new ResponseBase<NotaDtoGet>();
//		
//		try {
//			NotaDtoGet notaDto = NotaMapperDtoGet.toDtoGet(notaService.findById(id));
//			response.setResponse(notaDto);
//			response.setCode(200);
//			response.setMessage("OK");
//		} catch (NoSuchElementException e) {
////			e.printStackTrace();
//			response.setCode(500);
//			response.setMessage("KO");
//		
//		}
//		return response;
//	}
	
	@GetMapping(value = "/{id}")
	public Nota findById(@PathVariable Long id) {
		return notaService.findById(id);
	}
	
//	@GetMapping(value = "/findbycheck")
//	public ResponseBase<List<Nota>> findByCheck(@RequestParam Boolean check) {
//		
//		ResponseBase<List<Nota>> response = new ResponseBase<List<Nota>>();
//		
//		try {
//			response.setResponse(notaService.findByIsCheck(check));
//			response.setCode(200);
//			response.setMessage("OK");
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//			response.setCode(500);
//			response.setMessage("KO");
//		}
//		
//		return response;
//		
//	}
	
	@GetMapping(value = "/findbycheck")
	public List<Nota> findByCheck(@RequestParam Boolean check) throws Exception {
		
		return notaService.findByIsCheck(check);
	}
	
//	@GetMapping(value = "/expired")
//	public ResponseBase<List<Nota>> findExpiredList(Date todayDate) {
//		todayDate = new Date();
//		ResponseBase<List<Nota>> response = new ResponseBase<List<Nota>>();
//		try {
//			response.setResponse(notaService.findByExpiredList(todayDate));
//			response.setCode(200);
//			response.setMessage("OK");
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//			response.setCode(500);
//			response.setMessage("KO");
//		}
//		
//		return response;
//	}
	
	@GetMapping(value = "/expired")
	public List<Nota> findExpiredList(Date todayDate) {
		todayDate = new Date();
		return notaService.findByExpiredList(todayDate);
	}
	
	public  ResponseBase<List<Nota>> findWithFilters(@RequestParam(required = false) String all,
													 @RequestParam(required = false) Boolean check,
													 @RequestParam(required = false) Date todayDate) {
		ResponseBase<List<Nota>> response = new ResponseBase<List<Nota>>();
		
		if (all != null) {
			try {
				response.setResponse(notaService.findAll());
				response.setCode(200);
				response.setMessage("OK");
			} catch (Exception e) {
				System.out.println(e.getMessage());
				response.setCode(500);
				response.setMessage("KO");
			}
		} if (check != null) {
			try {
				response.setResponse(notaService.findByIsCheck(check));
				response.setCode(200);
				response.setMessage("OK");
			} catch (Exception e) {
				System.out.println(e.getMessage());
				response.setCode(500);
				response.setMessage("KO");
			}
		} if (todayDate != null) {
			try {
				response.setResponse(notaService.findByExpiredList(todayDate));
				response.setCode(200);
				response.setMessage("OK");
			} catch (Exception e) {
				System.out.println(e.getMessage());
				response.setCode(500);
				response.setMessage("KO");
			}
		}
		
		return response;
	}
	
	@GetMapping(value = "/findbycheckandexpire")
	public List<Nota> findByCheckAndExpiredList(@RequestParam Boolean check) {
	    return notaService.findByCheckAndExpiredList(check, new Date());
	}

	
//	@GetMapping(value = "/allget")
//	public ResponseBase<List<Nota>> allGetManager(@RequestParam(required = false) Boolean check,
//	                                              @RequestParam(required = false) Boolean expire) {
//	    ResponseBase<List<Nota>> response = new ResponseBase<List<Nota>>();
//
//	    try {
//	        List<Nota> result;
//
//	        if (check == null && expire == null) {
//	            result = findAll();
//	        } else if (check != null && expire == null) {
//	            result = findByCheck(check);
//	        } else if (check == null && expire != null) {
//	            result = findExpiredList(new Date());
//	        } else {
//	            result = findByCheckAndExpiredList(check);
//	        }
//
//	        response.setResponse(result);
//	        response.setCode(200);
//	        response.setMessage("OK");
//	    } catch (Exception e) {
//	        System.out.println(e.getMessage());
//	        response.setCode(500);
//	        response.setMessage("KO");
//	    }
//
//	    return response;
//	}

	@GetMapping(value = "/allget")
	public List<Nota> allGetManager(@RequestParam(required = false) Boolean check,
	                                @RequestParam(required = false) Boolean expire) throws Exception {
	    List<Nota> result;

	    if (check == null && expire == null) {
	        result = findAll();
	    } else if (check != null && expire == null) {
	        result = findByCheck(check);
	    } else if (check == null && expire != null) {
	        result = findExpiredList(new Date());
	    } else {
	        result = findByCheckAndExpiredList(check);
	    }

	    return result;
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

	//TODO aggiungere column definition per la datacreazione, cper cambiare solo determinati paramentri aggiungere 
	//@pathvariable id	
	
	@PutMapping(value = "/{id}")
	public ResponseBase<NotaDtoPost> updateNota(@RequestBody NotaDtoPost dto, 
												@PathVariable Long id) {
		ResponseBase<NotaDtoPost> response = new ResponseBase<NotaDtoPost>();
		
		try {
			Nota notaEntity = NotaMapperDtoPost.toEntity(dto);
			NotaDtoPost notaDtoPost = NotaMapperDtoPost.toDtoPost(notaService.updateNotaOnlyTitleAndDescr(notaEntity, id));
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
	
	@PutMapping(value = "/changechecked/{id}")
	public ResponseBase<NotaDtoGet> toChangeChecked(@PathVariable Long id, 
													@RequestParam Boolean checked) throws Exception {
		ResponseBase<NotaDtoGet> response = new ResponseBase<NotaDtoGet>();
		
		try {
			NotaDtoGet notaDto = NotaMapperDtoGet.toDtoGet(notaService.toChangeChecked(id, checked));
			response.setResponse(notaDto);
			response.setCode(200);
			response.setMessage("OK");
		} catch (NoSuchElementException e) {
			response.setCode(500);
			response.setMessage("KO");
		
		}
		return response;
	}
	
	@PutMapping(value = "/addcategoria")
	public ResponseBase<Nota> aggiungiCategoria(@RequestParam Long idNota,
												@RequestParam Long idCategoria) {
		ResponseBase<Nota> response = new ResponseBase<Nota>();
		
		try {
			response.setResponse(notaService.addCategoria(idNota, idCategoria));
			response.setCode(200);
			response.setMessage("OK");
		} catch ( NoSuchElementException e) {
			response.setCode(500);
			response.setMessage("KO");
		}
		return response;							
	}
}
