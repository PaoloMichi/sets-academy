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

import it.sets.resource.v3.model.Subcategory;
import it.sets.resource.v3.service.SubcategoryService;
import it.sets.resource.v3.web.dto.SubcategoryDto;

@RestController
@RequestMapping(SubcategoryController.URI_SPEC)
public class SubcategoryController {

    @Autowired
    SubcategoryService subcategoryService;

    protected static final String URI_SPEC = "/subcategory";

    @GetMapping(value = "")
    public List<Subcategory> findAll() {
        return subcategoryService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Subcategory findById(@PathVariable Long id) {
        return subcategoryService.findById(id);
    }

    @PostMapping(value = "")
    public Subcategory save(@RequestBody SubcategoryDto subcategoryDto) {
        return subcategoryService.add(subcategoryDto);
    }

    @PutMapping(value = "")
    public Subcategory update(@RequestBody SubcategoryDto subcategoryDto) {
        return subcategoryService.update(subcategoryDto);
    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable Long id) {
        subcategoryService.deleteById(id);
        return "OK";
    }
}
