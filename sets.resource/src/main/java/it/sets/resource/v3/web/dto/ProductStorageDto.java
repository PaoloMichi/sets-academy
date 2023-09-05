package it.sets.resource.v3.web.dto;

public class ProductStorageDto {
	private Long idStorageFrom;
	private Long idStorageTo;
	private Long idProduct;
	private Integer amount;
	
	public ProductStorageDto() {
		super();
	}

	public ProductStorageDto(Long idStorageFrom, Long idStorageTo, Long idProduct, Integer amount) {
		super();
		this.idStorageFrom = idStorageFrom;
		this.idStorageTo = idStorageTo;
		this.idProduct = idProduct;
		this.amount = amount;
	}

	public Long getIdStorageFrom() {
		return idStorageFrom;
	}

	public void setIdStorageFrom(Long idStorageFrom) {
		this.idStorageFrom = idStorageFrom;
	}

	public Long getIdStorageTo() {
		return idStorageTo;
	}

	public void setIdStorageTo(Long idStorageTo) {
		this.idStorageTo = idStorageTo;
	}

	public Long getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
}