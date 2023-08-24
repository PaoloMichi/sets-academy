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

import it.sets.resource.v3.model.Category;
import it.sets.resource.v3.service.CategoryService;

@RestController
@RequestMapping(CategoryController.URI_SPEC)
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    protected static final String URI_SPEC = "/category";

    @GetMapping(value = "")
    public List<Category> findAll() {
        return categoryService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Category findById(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @PostMapping(value = "")
    public Category save(@RequestBody Category category) {
        category.setId(null);
        return categoryService.save(category);
    }

    @PutMapping(value = "")
    public Category update(@RequestBody Category category) {
        return categoryService.save(category);
    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable Long id) {
        categoryService.deleteById(id);
        return "OK";
    }
}
