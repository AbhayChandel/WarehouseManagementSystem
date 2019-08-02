package com.zerosolutions.warehousemanagementsystem.stock.business.api.dto.mapper;

import com.zerosolutions.warehousemanagementsystem.stock.business.api.dto.ItemCategoryDto;
import com.zerosolutions.warehousemanagementsystem.stock.data.entity.ItemCategoryEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemCategoryMapper {

    ItemCategoryDto toDto(ItemCategoryEntity itemCategoryEntity);

    List<ItemCategoryDto> toDtos(List<ItemCategoryEntity> itemCategoryEntities);

    ItemCategoryEntity toEntity(ItemCategoryDto itemCategoryDto);
}
