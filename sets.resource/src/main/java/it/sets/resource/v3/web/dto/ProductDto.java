package it.sets.resource.v3.web.dto;

public class ProductDto {
    
	private Long id;
    private String name;
    private String code;
    private Boolean prescriptionRequired;
    private Long subcategoryId;
	
    public ProductDto() {
		super();
	}

	public ProductDto(Long id, String name, String code, Boolean prescriptionRequired, Long subcategoryId) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.prescriptionRequired = prescriptionRequired;
		this.subcategoryId = subcategoryId;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Boolean getPrescriptionRequired() {
		return prescriptionRequired;
	}

	public void setPrescriptionRequired(Boolean prescriptionRequired) {
		this.prescriptionRequired = prescriptionRequired;
	}

	public Long getSubcategoryId() {
		return subcategoryId;
	}

	public void setSubcategoryId(Long subcategoryId) {
		this.subcategoryId = subcategoryId;
	}
    
}
