package it.sets.resource.v3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.sets.resource.v3.model.Category;
import it.sets.resource.v3.model.Subcategory;
import it.sets.resource.v3.repository.CategoryRepository;
import it.sets.resource.v3.repository.SubcategoryRepository;
import it.sets.resource.v3.web.dto.SubcategoryDto;
import it.sets.resource.v3.web.dtoMapper.SubcategoryMapper;

@Service
public class SubcategoryService {

    @Autowired
    SubcategoryRepository subcategoryRepository;
    
    @Autowired
    CategoryRepository categoryRepository;

    public List<Subcategory> findAll() {
        return subcategoryRepository.findAll();
    }

    public Subcategory findById(Long id) {
        Subcategory subcategory = subcategoryRepository.findById(id).orElse(null);
        return subcategory;
    }

    public Subcategory add(SubcategoryDto subcategoryDto) {
    	subcategoryDto.setId(null);
    	Category category = null;
    	if(categoryRepository.existsById(subcategoryDto.getCategoryId())) {
    		category = categoryRepository.findById(subcategoryDto.getCategoryId()).get();
    	}
    	Subcategory subcategory = SubcategoryMapper.toEntity(subcategoryDto, category);
        return subcategoryRepository.save(subcategory);
    }
    
    public Subcategory update(SubcategoryDto subcategoryDto) {
    	subcategoryDto.setId(null);
    	Category category = null;
    	if(categoryRepository.existsById(subcategoryDto.getCategoryId())) {
    		category = categoryRepository.findById(subcategoryDto.getCategoryId()).get();
    	}
    	Subcategory subcategory = SubcategoryMapper.toEntity(subcategoryDto, category);
        return subcategoryRepository.save(subcategory);
    }

    public void deleteById(Long id) {
        Subcategory subcategory = subcategoryRepository.findById(id).orElse(null);
        if (subcategory != null) {
            subcategoryRepository.delete(subcategory);
        }
    }
}
