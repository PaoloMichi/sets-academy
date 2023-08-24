package it.sets.resource.v3.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import it.sets.resource.v3.model.Product;
import it.sets.resource.v3.model.ProductStored;
import it.sets.resource.v3.model.ProductStoredPk;
import it.sets.resource.v3.model.Storage;
import it.sets.resource.v3.repository.ProductRepository;
import it.sets.resource.v3.repository.ProductStoredRepository;
import it.sets.resource.v3.repository.StorageRepository;
import it.sets.resource.v3.web.dto.ProductStoredDto;


@Service
public class ProductStoredService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StorageRepository storageRepository;

    @Autowired
    private ProductStoredRepository productStoredRepository;
    

    public void addProductStored(ProductStoredDto productStoredDto) {
 //    	log.info("productStoredService-addProductStored: productId = " + productStoredDto.getId1() + " - storageId = " + productStoredDto.getId2());
    	Product product = null;
    	Storage storage = null;
    	try {
			product = productRepository.findById(productStoredDto.getId1()).get();
			storage = storageRepository.findById(productStoredDto.getId2()).get();
		} catch (NoSuchElementException e) {
//			log.error(e.getMessage());
			throw e;
		}
        
        ProductStoredPk productStoredPk = new ProductStoredPk(productStoredDto.getId1(), productStoredDto.getId2());
        ProductStored productStored = new ProductStored(productStoredPk, product, storage, productStoredDto.getAmount());
        //se a db viene inserito un nuovo productStored anche passandogli gli oggeti product e storage nulli non è necessario il try-catch
        //l'errore si gestisce o controllando se esistono gli id o l'eventuale exception che torna. (nel nostro caso IllegalArgumentException)
        productStoredRepository.save(productStored);
    }


	public List<ProductStored> findAll() {
		return productStoredRepository.findAll();
		
	}
}
