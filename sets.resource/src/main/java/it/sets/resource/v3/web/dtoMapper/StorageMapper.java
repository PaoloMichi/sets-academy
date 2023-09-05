package it.sets.resource.v3.web.dtoMapper;

import it.sets.resource.v3.model.Category;
import it.sets.resource.v3.model.Storage;
import it.sets.resource.v3.web.dto.StorageDto;

public class StorageMapper {
	public static StorageDto toDto(Storage entity) {
		StorageDto dto = new StorageDto();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setMaxAmount(entity.getMaxAmount());
		dto.setBaseCategoryId(entity.getBaseCategory().getId());
		return dto;
	}
	
	public static Storage toEntity(StorageDto dto, Category baseCategory) {
		Storage entity = new Storage();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setMaxAmount(dto.getMaxAmount());
		entity.setBaseCategory(baseCategory);
		return entity; 
	}
}
