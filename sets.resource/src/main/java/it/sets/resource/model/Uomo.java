package it.sets.resource.model;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Uomo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private int eta;
	
	@Column
	private Date birth;
	
//	@ManyToMany (mappedBy = "uomini")
//	Set<Donna> donne;
	
	@OneToMany (mappedBy = "uomo")
	@JsonIgnore
	Set<Couple> coppie;

	public Uomo() {
		super();
	}

	public Uomo(Long id, String name, int eta, Date birth, Set<Couple> coppie) {
		super();
		this.id = id;
		this.name = name;
		this.eta = eta;
		this.birth = birth;
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

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public Set<Couple> getCoppie() {
		return coppie;
	}

	public void setCoppie(Set<Couple> coppie) {
		this.coppie = coppie;
	}

	
	
}
