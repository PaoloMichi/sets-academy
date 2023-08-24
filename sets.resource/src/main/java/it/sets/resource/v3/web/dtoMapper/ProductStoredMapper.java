package it.sets.resource.v3.web.dtoMapper;

import it.sets.resource.v3.model.ProductStored;
import it.sets.resource.v3.model.ProductStoredPk;
import it.sets.resource.v3.web.dto.ProductStoredDto;

public class ProductStoredMapper {
    
    public static ProductStored toEntity(ProductStoredDto dto) {
        ProductStoredPk pk = new ProductStoredPk(dto.getId1(), dto.getId2());
        ProductStored entity = new ProductStored();
        entity.setId(pk);
        entity.setAmount(dto.getAmount());
        return entity;
    }
    
    public static ProductStoredDto toDto(ProductStored entity) {
        ProductStoredDto dto = new ProductStoredDto();
        dto.setId1(entity.getId().getProductId());
        dto.setId2(entity.getId().getStorageId());
        dto.setAmount(entity.getAmount());
        return dto;
    }
}
