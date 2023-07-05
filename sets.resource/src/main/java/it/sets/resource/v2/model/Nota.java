package it.sets.resource.v2.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PreUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Nota {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(insertable = false, updatable = false, columnDefinition="DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP")
	private Date creationDate;
	 
	
	@Column(insertable = false, updatable = true)
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

	@ManyToOne
	@JsonIgnore
    private Categoria categoria;
	
	public Nota() {
		super();
	}

	public Nota(Long id, Date creationDate, Date lastModifyDate, Boolean checked, String title, String description,
			Date expireDate, Priority priority, Categoria categoria) {
		super();
		this.id = id;
		this.creationDate = creationDate;
		this.lastModifyDate = lastModifyDate;
		this.checked = checked;
		this.title = title;
		this.description = description;
		this.expireDate = expireDate;
		this.priority = priority;
		this.categoria = categoria;
	}

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

	@PreUpdate
	public void preUpdate() {
	    this.lastModifyDate = new Date();
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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
