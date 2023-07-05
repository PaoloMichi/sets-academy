package it.sets.resource.v2.web.dto;

public class NotaDtoGet {

	private Long id;
	
	private Boolean checked;

	public NotaDtoGet() {
		super();
	}
	
	
	
	public NotaDtoGet(Long id, Boolean checked) {
		super();
		this.id = id;
		this.checked = checked;
	}



	public Boolean getChecked() {
		return checked;
	}



	public void setChecked(Boolean checked) {
		this.checked = checked;
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

}
