package it.sets.resource.v3.web.dtoMapper;

import it.sets.resource.v3.model.Product;
import it.sets.resource.v3.model.Subcategory;
import it.sets.resource.v3.web.dto.ProductDto;

public class ProductMapper {
    public static ProductDto toDto(Product entity) {
        ProductDto dto = new ProductDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCode(entity.getCode());
        dto.setPrescriptionRequired(entity.getPrescriptionRequired());
        dto.setSubcategoryId(entity.getSubcategory().getId()); 
        return dto;
    }

    public static Product toEntity(ProductDto dto, Subcategory subcategory) {
        Product entity = new Product();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setCode(dto.getCode());
        entity.setPrescriptionRequired(dto.getPrescriptionRequired());
        entity.setSubcategory(subcategory);

        return entity;
    }
}
