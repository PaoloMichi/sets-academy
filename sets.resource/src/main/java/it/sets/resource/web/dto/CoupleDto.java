package it.sets.resource.web.dto;

import it.sets.resource.model.Donna;
import it.sets.resource.model.Uomo;

public class CoupleDto {

	Donna donna;
	Uomo uomo;
	
	public CoupleDto() {
		super();
	}

	public CoupleDto(Donna donna, Uomo uomo) {
		super();
		this.donna = donna;
		this.uomo = uomo;
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

