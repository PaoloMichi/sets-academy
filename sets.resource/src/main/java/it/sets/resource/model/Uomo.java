package it.sets.resource.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class Uomo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private int eta;
	
	@ManyToMany (mappedBy = "uomini")
	List<Donna> donne;

	public Uomo() {
		super();
	}

	
	public Uomo(Long id, String name, int eta, List<Donna> donne) {
		super();
		this.id = id;
		this.name = name;
		this.eta = eta;
		this.donne = donne;
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

	public int getEta() {
		return eta;
	}

	public void setEta(int eta) {
		this.eta = eta;
	}


	public List<Donna> getDonne() {
		return donne;
	}


	public void setDonne(List<Donna> donne) {
		this.donne = donne;
	}
	
	
}
