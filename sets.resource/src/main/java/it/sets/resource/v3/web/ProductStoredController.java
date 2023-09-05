package it.sets.resource.v3.web;

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
import org.springframework.web.bind.annotation.RestController;



import it.sets.resource.v3.model.Product;
import it.sets.resource.v3.model.ProductStored;
import it.sets.resource.v3.model.Storage;
import it.sets.resource.v3.service.ProductStoredService;
import it.sets.resource.v3.web.dto.ProductDto;
import it.sets.resource.v3.web.dto.ProductStorageDto;
import it.sets.resource.v3.web.dto.ProductStoredDto;

@RestController
@RequestMapping(ProductStoredController.URI_SPEC)
public class ProductStoredController {
	@Autowired
	private ProductStoredService productStoredService;
    
	protected static final String URI_SPEC = "/productstored";

    @GetMapping("")
    public List<ProductStored> findAll() {
    	return productStoredService.findAll();
    }
    
//    @GetMapping("/{id}")
//    public ProductStored findById(@PathVariable Long id) {
//    	return productStoredService.findById(id);
//    }
    
    @PostMapping("")
    public ProductStored save(@RequestBody ProductStoredDto productStoredDto) throws NoSuchElementException {
       return productStoredService.add(productStoredDto);
    }
    
    @PutMapping(value = "")
    public ProductStored update(@RequestBody ProductStoredDto productStoredDto) throws NoSuchElementException {
        return productStoredService.update(productStoredDto);
    }
    
//    @DeleteMapping(value = "/{id}")
//    public String delete(@PathVariable Long id) {
//        productStoredService.deleteById(id);
//        return "OK";
//    }
    
    @PutMapping(value = "/amount")
    public ProductStored changeAmount (@RequestBody ProductStoredDto productStoredDto) {
    	return productStoredService.changeAmount(productStoredDto);
    }
    
    @GetMapping(value = "/allprodsfromstorage/{storageId}")
    public List<Product> allProdFromStorage(@PathVariable Long storageId){
    	return productStoredService.allProdFromStorage(storageId);
    }
    
    @GetMapping(value = "/storagewithprod")
    public List<Storage> storageWithProd(){
    	return productStoredService.storageWithProd();
    }
    
    @PutMapping(value = "/moveprod")
    public String moveProdFromStor(@RequestBody ProductStorageDto productStorageDto) {
    	return	productStoredService.moveProdFromStor(productStorageDto);
    }
} 
