package it.sets.resource.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Propietario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String nome;
	
	@Column
	private Integer eta;
	
	@OneToMany(mappedBy = "propietario")
	private List<Casa> cases;

	public Propietario() {
		super();
	}

	

	public Propietario(Long id, String nome, Integer eta, List<Casa> cases) {
		super();
		this.id = id;
		this.nome = nome;
		this.eta = eta;
		this.cases = cases;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getEta() {
		return eta;
	}

	public void setEta(Integer eta) {
		this.eta = eta;
	}



	public List<Casa> getCases() {
		return cases;
	}



	public void setCases(List<Casa> cases) {
		this.cases = cases;
	}
	
}
