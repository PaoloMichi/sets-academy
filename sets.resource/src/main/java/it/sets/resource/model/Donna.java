package it.sets.resource.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Donna {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private int eta;

	@ManyToMany
	@JoinTable(
			name = "couple", 
			joinColumns = @JoinColumn(name = "donna_id"),
			inverseJoinColumns = @JoinColumn(name = "uomo_id")
	)
	List<Uomo> uomini;
	
	
	public Donna() {
		super();
	}

	public Donna(Long id, String name, int eta) {
		super();
		this.id = id;
		this.name = name;
		this.eta = eta;
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
	
	
}
