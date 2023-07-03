package it.sets.resource.v2.web.dto;

public class NotaDtoGet {

	private Long id;

	public NotaDtoGet() {
		super();
	}
	
	
	public NotaDtoGet(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

}
