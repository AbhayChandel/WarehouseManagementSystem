package com.zerosolutions.warehousemanagementsystem.stock.business.impl;

import com.zerosolutions.warehousemanagementsystem.stock.business.api.ItemCategory;
import com.zerosolutions.warehousemanagementsystem.stock.data.entity.ItemCategoryEntity;
import com.zerosolutions.warehousemanagementsystem.stock.data.repository.ItemCategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class ItemCategoryImplTest {

    @Autowired
    ItemCategory itemCategory;

    @MockBean
    ItemCategoryRepository itemCategoryRepository;

    @Test
    void findAll() {
        ItemCategoryEntity itemCategoryEntityApple = new ItemCategoryEntity("Apple");
        ItemCategoryEntity itemCategoryEntityCapsicum = new ItemCategoryEntity("Capsicum");
        List<ItemCategoryEntity> itemCategoryEntityList = new ArrayList<>();
        itemCategoryEntityList.add(itemCategoryEntityApple);
        itemCategoryEntityList.add(itemCategoryEntityCapsicum);
        when(itemCategoryRepository.findAll()).thenReturn(itemCategoryEntityList);
        List<ItemCategoryEntity> itemCategories = itemCategory.findAll();
        assertEquals(2, itemCategories.size());
    }

    @Test
    void saveItemCategory() {
        ItemCategoryEntity itemCategoryEntityMocked = new ItemCategoryEntity("Apple");
        itemCategoryEntityMocked.setId(15L);
        when(itemCategoryRepository.save(any())).thenReturn(itemCategoryEntityMocked);
        ItemCategoryEntity itemCategoryEntity = itemCategory.saveItemCategory("Apple");
        assertEquals(Long.valueOf(15), itemCategoryEntity.getId());

    }

    @Test
    void findItemCategoryById() {
        Optional<ItemCategoryEntity> itemCategoryEntityMocked = Optional.of(new ItemCategoryEntity("Apple"));
        when(itemCategoryRepository.findById(100L)).thenReturn(itemCategoryEntityMocked);
        ItemCategoryEntity itemCategoryEntity = itemCategory.findItemCategoryById(100L);
        assertEquals("Apple", itemCategoryEntity.getName());
    }

    @Test
    void findItemCategoryByName() {
        ItemCategoryEntity itemCategoryEntityMocked = new ItemCategoryEntity("Apple");
        itemCategoryEntityMocked.setId(50L);
        Optional<ItemCategoryEntity> itemCategoryEntityMockedOptional = Optional.of(itemCategoryEntityMocked);
        when(itemCategoryRepository.findByName("Apple")).thenReturn(itemCategoryEntityMockedOptional);
        ItemCategoryEntity itemCategoryEntity = itemCategory.findItemCategoryByName("Apple");
        assertEquals(Long.valueOf(50), itemCategoryEntity.getId());
    }
}