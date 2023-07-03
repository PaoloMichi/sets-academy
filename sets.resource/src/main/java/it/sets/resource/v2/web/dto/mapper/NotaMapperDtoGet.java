package it.sets.resource.v2.web.dto.mapper;

import it.sets.resource.v2.model.Nota;
import it.sets.resource.v2.web.dto.NotaDtoGet;

public class NotaMapperDtoGet {

	public static NotaDtoGet toDtoGet(Nota entity) {
		NotaDtoGet dto = new NotaDtoGet();
		dto.setId(entity.getId());
		return dto;
	}
	
	public static Nota toEntityGet(NotaDtoGet dto) {
		Nota entity = new Nota();		
		return entity;
	}
}
