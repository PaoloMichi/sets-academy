package it.sets.resource.v2.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import it.sets.resource.v2.model.Nota;

@Entity
public class Categoria {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private String titolo;
    
    @Column
    private String colore;
    
    @OneToMany(mappedBy = "categoria")
    private List<Nota> nota;

	public Categoria() {
		super();
	}


	public Categoria(Long id, String titolo, String colore, List<Nota> nota) {
		super();
		this.id = id;
		this.titolo = titolo;
		this.colore = colore;
		this.nota = nota;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getColore() {
		return colore;
	}

	public void setColore(String colore) {
		this.colore = colore;
	}


	public List<Nota> getNota() {
		return nota;
	}


	public void setNota(List<Nota> nota) {
		this.nota = nota;
	}
  
	
}
