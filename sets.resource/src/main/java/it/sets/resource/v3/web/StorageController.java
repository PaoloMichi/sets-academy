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

import it.sets.resource.v3.model.Storage;
import it.sets.resource.v3.service.StorageService;
import it.sets.resource.v3.web.dto.StorageDto;

@RestController
@RequestMapping(StorageController.URI_SPEC)
public class StorageController {

    @Autowired
    StorageService storageService;

    protected static final String URI_SPEC = "/storage";

    @GetMapping(value = "")
    public List<Storage> findAll() {
        return storageService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Storage findById(@PathVariable Long id) {
        return storageService.findById(id);
    }

    @PostMapping(value = "")
    public Storage save(@RequestBody StorageDto storageDto) {
        storageDto.setId(null);
        return storageService.add(storageDto);
    }

    @PutMapping(value = "")
    public Storage update(@RequestBody StorageDto storageDto) {
        storageDto.setId(null);
        return storageService.update(storageDto);
    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable Long id) {
        storageService.deleteById(id);
        return "OK";
    }
}
