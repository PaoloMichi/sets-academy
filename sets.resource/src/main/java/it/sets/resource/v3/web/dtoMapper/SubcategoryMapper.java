package it.sets.resource.v3.web.dtoMapper;

import it.sets.resource.v3.model.Category;
import it.sets.resource.v3.model.Subcategory;
import it.sets.resource.v3.web.dto.SubcategoryDto;

public class SubcategoryMapper {
    public static SubcategoryDto toDto(Subcategory entity) {
        SubcategoryDto dto = new SubcategoryDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDuration(entity.getDuration());
        dto.setCategoryId(entity.getCategory().getId());
        return dto;
    }

    public static Subcategory toEntity(SubcategoryDto dto, Category category) {
        Subcategory entity = new Subcategory();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDuration(dto.getDuration());
        entity.setCategory(category);
        return entity;
    }
}
