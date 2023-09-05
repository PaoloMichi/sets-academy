package it.sets.resource.v3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.sets.resource.v3.model.Category;
import it.sets.resource.v3.model.Storage;
import it.sets.resource.v3.repository.CategoryRepository;
import it.sets.resource.v3.repository.StorageRepository;
import it.sets.resource.v3.web.dto.StorageDto;
import it.sets.resource.v3.web.dtoMapper.StorageMapper;

@Service
public class StorageService {

    @Autowired
    StorageRepository storageRepository;
    
    @Autowired
    CategoryRepository categoryRepository;

    public List<Storage> findAll() {
        return storageRepository.findAll();
    }

    public Storage findById(Long id) {
        Storage storage = storageRepository.findById(id).orElse(null);
        return storage;
    }

    public Storage add(StorageDto storageDto) {
    	storageDto.setId(null);
    	Category baseCategory = null;
    	if(categoryRepository.existsById(storageDto.getBaseCategoryId())) {
    		baseCategory = categoryRepository.findById(storageDto.getBaseCategoryId()).get();
    	}
    	Storage storage = StorageMapper.toEntity(storageDto, baseCategory);
        return storageRepository.save(storage);
    }

    public Storage update(StorageDto storageDto) {
    	storageDto.setId(null);
    	Category baseCategory = null;
    	if(categoryRepository.existsById(storageDto.getBaseCategoryId())) {
    		baseCategory = categoryRepository.findById(storageDto.getBaseCategoryId()).get();
    	}
    	Storage storage = StorageMapper.toEntity(storageDto, baseCategory);
        return storageRepository.save(storage);
    }
    
    public void deleteById(Long id) {
        Storage storage = storageRepository.findById(id).orElse(null);
        if (storage != null) {
            storageRepository.delete(storage);
        }
    }
}
