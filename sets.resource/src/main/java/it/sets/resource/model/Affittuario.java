package it.sets.resource.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Affittuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String nome;

	@Column
	private Integer eta;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "casa_id")
	private Casa casa;
	

	public Affittuario() {
		super();
	}

	public Affittuario(Long id, String nome, Integer eta, Casa casa) {
		super();
		this.id = id;
		this.nome = nome;
		this.eta = eta;
		this.casa = casa;
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

	public Casa getCasa() {
		return casa;
	}

	public void setCasa(Casa casa) {
		this.casa = casa;
	}
	
	//test commit
}
