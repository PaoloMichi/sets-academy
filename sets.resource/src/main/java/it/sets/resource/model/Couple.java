package it.sets.resource.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Couple {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn (name = "donna_id")
	Donna donna;
	
	@ManyToOne
	@JoinColumn (name = "uomo_id")
	Uomo uomo;

	public Couple() {
		super();
	}

	public Couple(Long id, Donna donna, Uomo uomo) {
		super();
		this.id = id;
		this.donna = donna;
		this.uomo = uomo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Donna getDonna() {
		return donna;
	}

	public void setDonna(Donna donna) {
		this.donna = donna;
	}

	public Uomo getUomo() {
		return uomo;
	}

	public void setUomo(Uomo uomo) {
		this.uomo = uomo;
	}
	
}
