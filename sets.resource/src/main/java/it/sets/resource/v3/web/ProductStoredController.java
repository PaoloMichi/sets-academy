package it.sets.resource.v3.web;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.sets.resource.v3.model.ProductStored;
import it.sets.resource.v3.service.ProductStoredService;
import it.sets.resource.v3.web.dto.ProductStoredDto;

@RestController
@RequestMapping("/productstored")
public class ProductStoredController {

    @Autowired
    private ProductStoredService productStoredService;
    
    @GetMapping("")
    public List<ProductStored> findAll() {
    	return productStoredService.findAll();
    }
    
    @PostMapping("")
    public void addProductStored(@RequestBody ProductStoredDto productStoredDto) throws NoSuchElementException {
        productStoredService.addProductStored(productStoredDto);
    }
}
