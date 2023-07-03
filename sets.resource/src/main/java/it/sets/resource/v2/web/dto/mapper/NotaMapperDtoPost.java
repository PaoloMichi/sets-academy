package it.sets.resource.v2.web.dto.mapper;

import it.sets.resource.v2.model.Nota;
import it.sets.resource.v2.web.dto.NotaDtoPost;

public class NotaMapperDtoPost {
	
	public static NotaDtoPost toDtoPost(Nota entity) {
		NotaDtoPost dto = new NotaDtoPost();
		dto.setId(entity.getId());
		dto.setChecked(entity.getChecked());
		dto.setTitle(entity.getTitle());
		dto.setDescription(entity.getDescription());
		dto.setExpireDate(entity.getExpireDate());
		dto.setPriority(entity.getPriority());

		return dto;
	}
	
	public static Nota toEntity(NotaDtoPost dto) {
		Nota entity = new Nota();
		entity.setId(dto.getId());
		entity.setChecked(dto.getChecked());
		entity.setTitle(dto.getTitle());
		entity.setDescription(dto.getDescription());
		entity.setExpireDate(dto.getExpireDate());
		entity.setPriority(dto.getPriority());

		return entity;
		
	}
}
