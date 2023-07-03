package it.sets.resource.v2.web.dto;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import it.sets.resource.v2.model.Priority;


public class NotaDtoPost {
	
	private Long id;
	private Boolean checked;
	private String title;
	private String description;
	private Date expireDate;
	
	@Enumerated(EnumType.STRING)
	Priority priority;
	
	public NotaDtoPost() {
		super();
	}
	
	public NotaDtoPost(Long id, Boolean checked, String title, String description, Date expireDate, Priority priority) {
		super();
		this.id = id;
		this.checked = checked;
		this.title = title;
		this.description = description;
		this.expireDate = expireDate;
		this.priority = priority;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	
}

