package it.sets.resource.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Donna {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private int eta;

//	@ManyToMany (fetch = FetchType.LAZY)
//	@JoinTable(
//			name = "couple", 
//			joinColumns = @JoinColumn(name = "donna_id"),
//			inverseJoinColumns = @JoinColumn(name = "uomo_id")
//	)
//	
//	Set<Uomo> uomini = new HashSet<>();	
	
	@OneToMany(mappedBy = "donna")
	@JsonIgnore
	Set<Couple> coppie;
	
	public Donna() {
		super();
	}

	public Donna(Long id, String name, int eta, Set<Couple> coppie) {
		super();
		this.id = id;
		this.name = name;
		this.eta = eta;
		this.coppie = coppie;
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

	public Set<Couple> getCoppie() {
		return coppie;
	}

	public void setCoppie(Set<Couple> coppie) {
		this.coppie = coppie;
	}

	
	
	
}
