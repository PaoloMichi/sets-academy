package it.sets.resource.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Casa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String indirizzo;
	
	@Column
	private Integer metratura;
	
	@Column
	private String dettagli;
	
	@OneToMany(mappedBy = "casa", cascade = CascadeType.REMOVE)
	private List<Stanza> stanze;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "propietario_id")
	private Propietario propietario;

	@OneToMany(mappedBy = "casa", cascade = CascadeType.REMOVE)
	private List<Affittuario> affittuari;
	
	public Casa() {
		super();
	}

	public Casa(Long id, String indirizzo, Integer metratura, String dettagli, List<Stanza> stanze,
			Propietario propietario, List<Affittuario> affittuari) {
		super();
		this.id = id;
		this.indirizzo = indirizzo;
		this.metratura = metratura;
		this.dettagli = dettagli;
		this.stanze = stanze;
		this.propietario = propietario;
		this.affittuari = affittuari;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public Integer getMetratura() {
		return metratura;
	}

	public void setMetratura(Integer metratura) {
		this.metratura = metratura;
	}



	public List<Stanza> getStanze() {
		return stanze;
	}


	public void setStanze(List<Stanza> stanze) {
		this.stanze = stanze;
	}


	public String getDettagli() {
		return dettagli;
	}

	public void setDettagli(String dettagli) {
		this.dettagli = dettagli;
	}
	
	public Propietario getPropietario() {
		return propietario;
	}

	public void setPropietario(Propietario propietario) {
		this.propietario = propietario;
	}

	public List<Affittuario> getAffittuari() {
		return affittuari;
	}

	public void setAffittuari(List<Affittuario> affittuari) {
		this.affittuari = affittuari;
	}
}
