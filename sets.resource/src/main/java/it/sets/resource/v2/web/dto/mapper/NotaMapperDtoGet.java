package it.sets.resource.v2.web.dto.mapper;

import it.sets.resource.v2.model.Nota;
import it.sets.resource.v2.web.dto.NotaDtoGet;

public class NotaMapperDtoGet {

	public static NotaDtoGet toDtoGet(Nota entity) {
		NotaDtoGet dto = new NotaDtoGet();
		dto.setId(entity.getId());
		dto.setChecked(entity.getChecked());
		return dto;
	}
	
	public static Nota toEntityGet(NotaDtoGet dto) {
		Nota entity = new Nota();		
		entity.setId(dto.getId());
		entity.setChecked(dto.getChecked());
		return entity;
	}
}
