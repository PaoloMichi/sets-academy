package it.sets.resource.v3.web.dto;

public class SubcategoryDto {
    
    private Long id;
    private String name;
    private String duration;
    private Long categoryId;
	
    public SubcategoryDto() {
		super();
	}

	public SubcategoryDto(Long id, String name, String duration, Long categoryId) {
		super();
		this.id = id;
		this.name = name;
		this.duration = duration;
		this.categoryId = categoryId;
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

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
    
}
