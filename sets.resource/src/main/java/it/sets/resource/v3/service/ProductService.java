package it.sets.resource.v3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.sets.resource.v3.model.Product;
import it.sets.resource.v3.model.Subcategory;
import it.sets.resource.v3.repository.ProductRepository;
import it.sets.resource.v3.repository.SubcategoryRepository;
import it.sets.resource.v3.web.dto.ProductDto;
import it.sets.resource.v3.web.dtoMapper.ProductMapper;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    
    @Autowired
    SubcategoryRepository subcategoryRepository;
    
    
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        return product;
    }

    public Product add(ProductDto productDto) {
    	productDto.setId(null);
    	Subcategory subcategory = null;
    	if(subcategoryRepository.existsById(productDto.getSubcategoryId())) {	
    		subcategory = subcategoryRepository.findById(productDto.getSubcategoryId()).get();
    	}
    	Product product = ProductMapper.toEntity(productDto, subcategory);
    	return productRepository.save(product);
    }
    
    public Product update(ProductDto productDto) {
    	Subcategory subcategory = null;
    	if(subcategoryRepository.existsById(productDto.getSubcategoryId())) {	
    		subcategory = subcategoryRepository.findById(productDto.getSubcategoryId()).get();
    	}
    	Product product = ProductMapper.toEntity(productDto, subcategory);
    	return productRepository.save(product);
    }

    public void deleteById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            productRepository.delete(product);
        }
    }
}
