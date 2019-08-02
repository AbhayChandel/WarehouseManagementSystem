package com.zerosolutions.warehousemanagementsystem.stock.business.dto.mapper;

import com.zerosolutions.warehousemanagementsystem.stock.business.api.dto.ItemCategoryDto;
import com.zerosolutions.warehousemanagementsystem.stock.business.api.dto.mapper.ItemCategoryMapper;
import com.zerosolutions.warehousemanagementsystem.stock.data.entity.ItemCategoryEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemCategoryMapperTest {

    private ItemCategoryMapper mapper
            = Mappers.getMapper(ItemCategoryMapper.class);

    @Test
    void testMappingEntityToDto() {
        ItemCategoryEntity itemCategoryEntityMocked = new ItemCategoryEntity("Orange");
        itemCategoryEntityMocked.setId(15L);
        ItemCategoryDto itemCategoryDto = mapper.toDto(itemCategoryEntityMocked);
        assertEquals(Long.valueOf(15L), itemCategoryDto.getId());
        assertEquals("Orange", itemCategoryDto.getName());
    }

    @Test
    void testMappingEntityListToDtoList() {
        ItemCategoryEntity itemCategoryEntityMockedOrange = new ItemCategoryEntity("Orange");
        itemCategoryEntityMockedOrange.setId(15L);
        ItemCategoryEntity itemCategoryEntityMockedBanana = new ItemCategoryEntity("Banana");
        itemCategoryEntityMockedBanana.setId(16L);
        List<ItemCategoryEntity> itemCategoryEntitiesMocked = new ArrayList<>();
        itemCategoryEntitiesMocked.add(itemCategoryEntityMockedOrange);
        itemCategoryEntitiesMocked.add(itemCategoryEntityMockedBanana);
        List<ItemCategoryDto> itemCategoryDtos = mapper.toDtos(itemCategoryEntitiesMocked);
        assertEquals(2, itemCategoryDtos.size());
        assertEquals("Orange", itemCategoryDtos.get(0).getName());
        assertEquals("Banana", itemCategoryDtos.get(1).getName());
    }

    @Test
    void testMappingDtoToEntity() {
        ItemCategoryDto itemCategoryDtoMocked = new ItemCategoryDto();
        itemCategoryDtoMocked.setName("Orange");
        itemCategoryDtoMocked.setId(25L);
        ItemCategoryEntity itemCategoryEntity = mapper.toEntity(itemCategoryDtoMocked);
        assertEquals(Long.valueOf(25), itemCategoryEntity.getId());
        assertEquals("Orange", itemCategoryEntity.getName());
    }
}
