package it.sets.resource.v3.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ProductStoredPk implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long productId;
	
	private Long storageId;

	public ProductStoredPk() {
		super();
	}
	
	
	
	public ProductStoredPk(Long storageId) {
		super();
		this.storageId = storageId;
	}



	public ProductStoredPk(Long productId, Long storageId) {
		super();
		this.productId = productId;
		this.storageId = storageId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getStorageId() {
		return storageId;
	}

	public void setStorageId(Long storageId) {
		this.storageId = storageId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
