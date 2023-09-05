package it.sets.resource.v3.web.dto;

public class ProductStoredDto {
    
    private Long productId;
    private Long storageId;
    private Integer amount;
	
    public ProductStoredDto() {
		super();
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

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

    
}
