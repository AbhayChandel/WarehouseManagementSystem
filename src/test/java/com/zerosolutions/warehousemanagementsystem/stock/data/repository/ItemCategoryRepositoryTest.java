package com.zerosolutions.warehousemanagementsystem.stock.data.repository;

import com.zerosolutions.warehousemanagementsystem.stock.data.entity.ItemCategoryEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class ItemCategoryRepositoryTest {

    @Autowired
    ItemCategoryRepository itemCategoryRepository;

    @Test
    void testFindAllCategories() {
        Iterable<ItemCategoryEntity> itemCategories = itemCategoryRepository.findAll();
        assertEquals(2, StreamSupport.stream(itemCategories.spliterator(), false).count());
    }

    @Test
    void testSavingItemCategory() {
        ItemCategoryEntity itemCategoryEntity = new ItemCategoryEntity("Banana");
        itemCategoryEntity = itemCategoryRepository.save(itemCategoryEntity);
        assertTrue(itemCategoryEntity.getId() > 0);
    }

    @Test
    void testFindItemCategoryById() {
        ItemCategoryEntity itemCategoryEntity = new ItemCategoryEntity("Banana");
        itemCategoryEntity = itemCategoryRepository.save(itemCategoryEntity);
        assertTrue(itemCategoryRepository.findById(itemCategoryEntity.getId()).isPresent());
    }

    @Test
    void testFindItemCategoryByName() {
        Optional<ItemCategoryEntity> itemCategoryOptional = itemCategoryRepository.findByName("Apple");
        assertTrue(itemCategoryOptional.isPresent());
    }

}