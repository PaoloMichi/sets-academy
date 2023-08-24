package it.sets.resource.v3.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class ProductStored implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProductStoredPk id = new ProductStoredPk();
	
	@ManyToOne(fetch = FetchType.EAGER)
	@MapsId("productId")
	private Product product;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@MapsId("storageId")
	private Storage storage;
	
	@Column(name = "amount")
	private Integer amount;

	public ProductStored() {
		super();
	}

	public ProductStored(ProductStoredPk id, Product product, Storage storage, Integer amount) {
		super();
		this.id = id;
		this.product = product;
		this.storage = storage;
		this.amount = amount;
	}

	public ProductStoredPk getId() {
		return id;
	}

	public void setId(ProductStoredPk id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Storage getStorage() {
		return storage;
	}

	public void setStorage(Storage storage) {
		this.storage = storage;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
}