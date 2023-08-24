package it.sets.resource.v3.web;

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

import it.sets.resource.v3.model.Product;
import it.sets.resource.v3.service.ProductService;
import it.sets.resource.v3.web.dto.ProductDto;

@RestController
@RequestMapping(ProductController.URI_SPEC)
public class ProductController {

    @Autowired
    ProductService productService;

    protected static final String URI_SPEC = "/product";

    @GetMapping(value = "")
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Product findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @PostMapping(value = "")
    public Product save(@RequestBody ProductDto productDto) {
        return productService.add(productDto);
    }

    @PutMapping(value = "")
    public Product update(@RequestBody ProductDto productDto) {
        return productService.update(productDto);
    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable Long id) {
        productService.deleteById(id);
        return "OK";
    }
}
