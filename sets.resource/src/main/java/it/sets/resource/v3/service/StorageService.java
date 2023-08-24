package it.sets.resource.v3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.sets.resource.v3.model.Storage;
import it.sets.resource.v3.repository.StorageRepository;

@Service
public class StorageService {

    @Autowired
    StorageRepository storageRepository;

    public List<Storage> findAll() {
        return storageRepository.findAll();
    }

    public Storage findById(Long id) {
        Storage storage = storageRepository.findById(id).orElse(null);
        return storage;
    }

    public Storage save(Storage storage) {
        return storageRepository.save(storage);
    }

    public void deleteById(Long id) {
        Storage storage = storageRepository.findById(id).orElse(null);
        if (storage != null) {
            storageRepository.delete(storage);
        }
    }
}
