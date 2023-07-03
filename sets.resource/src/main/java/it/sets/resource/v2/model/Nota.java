package it.sets.resource.v2.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.print.attribute.standard.DateTimeAtCompleted;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity

public class Nota {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column 
	private Date creationDate;
	 
	@Column 
	private Date lastModifyDate;
	 
	@Column
	private Boolean checked;
	
	@Column
	private String title;
	
	@Column
	private String description;
	
	@Column 
	private Date expireDate;
	 
	@Column
	@Enumerated(EnumType.STRING)
	private Priority priority;

	public Nota() {
		super();
	}

	public Nota(Long id, Date creationDate, Date lastModifyDate, Boolean checked, String title, String description,
			Date expireDate, Priority priority) {
		super();
		this.id = id;
		this.creationDate = creationDate;
		this.lastModifyDate = lastModifyDate;
		this.checked = checked;
		this.title = title;
		this.description = description;
		this.expireDate = expireDate;
		this.priority = priority;
	}
	
	

//	public Nota(Long id, String title, String description, Priority priority) {
//		super();
//		this.id = id;
//		this.creationDate = new Date();
//		this.lastModifyDate = new Date();
//		this.checked = false;
//		this.title = title;
//		this.description = description;
//		
//		Calendar calendar = Calendar.getInstance();
//        calendar.setTime(creationDate);
//        calendar.add(Calendar.MONTH, 6);
//        this.expireDate = calendar.getTime();
//			
//		this.priority = priority;
//	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLastModifyDate() {
		return lastModifyDate;
	}

	public void setLastModifyDate(Date lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
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
