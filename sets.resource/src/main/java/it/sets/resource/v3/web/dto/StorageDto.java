package it.sets.resource.v3.web.dto;

public class StorageDto {
	private Long id;
	private String name;
	private Integer maxAmount;
	private Long baseCategoryId;
	
	public StorageDto() {
		super();
	}
	
	public StorageDto(Long id, String name, Integer maxAmount, Long baseCategoryId) {
		super();
		this.id = id;
		this.name = name;
		this.maxAmount = maxAmount;
		this.baseCategoryId = baseCategoryId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(Integer maxAmount) {
		this.maxAmount = maxAmount;
	}

	public Long getBaseCategoryId() {
		return baseCategoryId;
	}

	public void setBaseCategoryId(Long baseCategoryId) {
		this.baseCategoryId = baseCategoryId;
	}
	
	
}
