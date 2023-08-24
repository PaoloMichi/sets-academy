package it.sets.resource.v3.web.dto;

public class ProductStoredDto {
    
    private Long id1;
    private Long id2;
    private Integer amount;
	
    public ProductStoredDto() {
		super();
	}

	public Long getId1() {
		return id1;
	}

	public void setId1(Long id1) {
		this.id1 = id1;
	}

	public Long getId2() {
		return id2;
	}

	public void setId2(Long id2) {
		this.id2 = id2;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

    
}
