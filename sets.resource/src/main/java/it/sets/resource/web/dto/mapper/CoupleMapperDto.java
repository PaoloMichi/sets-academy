package it.sets.resource.web.dto.mapper;

import it.sets.resource.model.Couple;
import it.sets.resource.web.dto.CoupleDto;

public class CoupleMapperDto {
	public static CoupleDto toDto(Couple entity) {
		CoupleDto dto = new CoupleDto();
		dto.setUomo(entity.getUomo());
		dto.setDonna(entity.getDonna());
		return dto;
	}
	
	public static Couple toEntity(CoupleDto dto) {
		Couple entity = new Couple();
		entity.setUomo(dto.getUomo());
		entity.setDonna(dto.getDonna());
		entity.setDiffEta(Math.abs(entity.getUomo().getEta()-entity.getDonna().getEta()));
		return entity;
	}
}
