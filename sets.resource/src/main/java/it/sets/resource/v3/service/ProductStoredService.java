package it.sets.resource.v3.service;

import java.util.ArrayList;
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
import it.sets.resource.v3.web.dto.ProductStorageDto;
import it.sets.resource.v3.web.dto.ProductStoredDto;


@Service
public class ProductStoredService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StorageRepository storageRepository;

    @Autowired
    private ProductStoredRepository productStoredRepository;
    
    public List<ProductStored> findAll() {
    	return productStoredRepository.findAll();
    	
    }
    
    
//    public ProductStored findById(Long id) {
//    	ProductStored productStored = productStoredRepository.findById(id).get();
//    	return productStored;
//    }

    public ProductStored add(ProductStoredDto productStoredDto) {
 //    	log.info("productStoredService-addProductStored: productId = " + productStoredDto.getId1() + " - storageId = " + productStoredDto.getId2());
    	Product product = null;
    	Storage storage = null;
    	try {
			product = productRepository.findById(productStoredDto.getProductId()).get();
			storage = storageRepository.findById(productStoredDto.getStorageId()).get();
		} catch (NoSuchElementException e) {
//			log.error(e.getMessage());
			throw e;
		}
        
        ProductStoredPk productStoredPk = new ProductStoredPk(productStoredDto.getProductId(), productStoredDto.getStorageId());
        ProductStored productStored = new ProductStored(productStoredPk, product, storage, productStoredDto.getAmount());
        //se a db viene inserito un nuovo productStored anche passandogli gli oggeti product e storage nulli non è necessario il try-catch
        //l'errore si gestisce o controllando se esistono gli id o l'eventuale exception che torna. (nel nostro caso IllegalArgumentException)
        return productStoredRepository.save(productStored);
    }

    public ProductStored update(ProductStoredDto productStoredDto) {
 //    	log.info("productStoredService-addProductStored: productId = " + productStoredDto.getId1() + " - storageId = " + productStoredDto.getId2());
    	Product product = null;
    	Storage storage = null;
    	try {
			product = productRepository.findById(productStoredDto.getProductId()).get();
			storage = storageRepository.findById(productStoredDto.getStorageId()).get();
		} catch (NoSuchElementException e) {
//			log.error(e.getMessage());
			throw e;
		}
        
        ProductStoredPk productStoredPk = new ProductStoredPk(productStoredDto.getProductId(), productStoredDto.getStorageId());
        ProductStored productStored = new ProductStored(productStoredPk, product, storage, productStoredDto.getAmount());
        //se a db viene inserito un nuovo productStored anche passandogli gli oggeti product e storage nulli non è necessario il try-catch
        //l'errore si gestisce o controllando se esistono gli id o l'eventuale exception che torna. (nel nostro caso IllegalArgumentException)
        return productStoredRepository.save(productStored);
    }


//	public void deleteById(Long id) {
//        ProductStored productStored = productStoredRepository.findById(id).get();
//        if (productStored != null) {
//            productStoredRepository.delete(productStored);
//        }
//	}


	public ProductStored changeAmount(ProductStoredDto productStoredDto) {
       
		ProductStoredPk productStoredPk = new ProductStoredPk(productStoredDto.getProductId(), productStoredDto.getStorageId());

		ProductStored productStored = productStoredRepository.findById(productStoredPk).get();
		
		productStored.setAmount(productStoredDto.getAmount());
		
		return productStoredRepository.save(productStored);
		
	}


	public List<Product> allProdFromStorage(Long storageId) {
		List<Product> prodsFromStorage = new ArrayList<>();
		List<ProductStored> productStoredByStorageId = productStoredRepository.findByStorageId(storageId);
		for (ProductStored productStored : productStoredByStorageId) {
			Product product = productStored.getProduct();
			prodsFromStorage.add(product);
		}
		
		return prodsFromStorage;
	}


	public List<Storage> storageWithProd() { 
		Integer amount = 0;
		List<Storage> StoragesWithProd = new ArrayList<>();
		List<ProductStored> productStoredWithAmount = productStoredRepository.findByAmountGreaterThanAndAmountIsNotNull(amount);
		for (ProductStored productStored : productStoredWithAmount) {
			Storage storage = productStored.getStorage();
			StoragesWithProd.add(storage);
		}

		return StoragesWithProd;
	}


	public String moveProdFromStor(ProductStorageDto productStorageDto) {
		ProductStoredPk productStoredPkFrom = new ProductStoredPk(productStorageDto.getIdProduct(), productStorageDto.getIdStorageFrom());
		ProductStored productStoredFrom = productStoredRepository.findById(productStoredPkFrom).get();
		productStoredFrom.setAmount(productStoredFrom.getAmount() - productStorageDto.getAmount());
		productStoredRepository.save(productStoredFrom);
		
		ProductStored productStoredTo = new ProductStored();
		ProductStoredPk productStoredPkTo = new ProductStoredPk(productStorageDto.getIdStorageTo(), productStorageDto.getIdProduct());
		productStoredTo.setId(productStoredPkTo);
		productStoredTo.setStorage(storageRepository.getById(productStorageDto.getIdStorageTo()));
		productStoredTo.setProduct(productRepository.getById(productStorageDto.getIdProduct()));
		productStoredTo.setAmount(productStorageDto.getAmount());
		productStoredRepository.save(productStoredTo);
		return "OK";
	}
	
}
