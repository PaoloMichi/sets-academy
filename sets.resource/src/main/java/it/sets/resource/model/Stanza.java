package it.sets.resource.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Stanza {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String description;
	
	@Column
	private Integer metratura;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name="casa_id")
	private Casa casa;



	public Stanza() {
		super();
	}

	public Stanza(Long id, String description, Integer metratura) {
		super();
		this.id = id;
		this.description = description;
		this.metratura = metratura;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getMetratura() {
		return metratura;
	}

	public void setMetratura(Integer metratura) {
		this.metratura = metratura;
	}
	
	
	
}
