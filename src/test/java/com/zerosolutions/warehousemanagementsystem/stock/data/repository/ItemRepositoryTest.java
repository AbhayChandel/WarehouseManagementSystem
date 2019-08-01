package com.zerosolutions.warehousemanagementsystem.stock.data.repository;

import com.zerosolutions.warehousemanagementsystem.stock.data.entity.FruitEntity;
import com.zerosolutions.warehousemanagementsystem.stock.data.entity.ItemEntity;
import com.zerosolutions.warehousemanagementsystem.stock.data.entity.VegetableEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @BeforeEach
    void setup() {
        FruitEntity fruitEntity = new FruitEntity();
        fruitEntity.setName("Golden Apple");
        fruitEntity.setCategory(1L);
        itemRepository.save(fruitEntity);
        fruitEntity = new FruitEntity();
        fruitEntity.setName("Washington Apple");
        fruitEntity.setCategory(1L);
        itemRepository.save(fruitEntity);
        VegetableEntity vegetableEntity = new VegetableEntity();
        vegetableEntity.setName("Red Capsicum");
        vegetableEntity.setCategory(2L);
        itemRepository.save(vegetableEntity);
        vegetableEntity = new VegetableEntity();
        vegetableEntity.setName("Green Capsicum");
        vegetableEntity.setCategory(2L);
        itemRepository.save(vegetableEntity);
    }

    @Test
    void testSavingFruit() {
        FruitEntity fruitEntity = new FruitEntity();
        fruitEntity.setName("Green Apple");
        fruitEntity.setCategory(1L);
        fruitEntity = itemRepository.save(fruitEntity);
        System.out.println(fruitEntity.getId());
        assertTrue(fruitEntity.getId() > 0);
    }

    @Test
    void testSavingVegetable() {
        VegetableEntity vegetableEntity = new VegetableEntity();
        vegetableEntity.setName("Yellow Capsicum");
        vegetableEntity.setCategory(2L);
        vegetableEntity = itemRepository.save(vegetableEntity);
        System.out.println(vegetableEntity.getId());
        assertTrue(vegetableEntity.getId() > 0);
    }

    @Test
    void testFindAllItems() {
        Iterable<ItemEntity> items = itemRepository.findAll();
        assertEquals(4, StreamSupport.stream(items.spliterator(), false).count());
    }

    @Test
    void testFindFruitByCategory() {
        List<ItemEntity> itemEntities = itemRepository.findByCategory(1L);
        assertEquals(2, itemEntities.size());
    }

    @Test
    void testFindFruitByName() {
        Optional<ItemEntity> item = itemRepository.findByName("Golden Apple");
        assertTrue(item.isPresent());
    }

    @Test
    void testFindVegetableByCategory() {
        List<ItemEntity> itemEntities = itemRepository.findByCategory(2L);
        assertEquals(2, itemEntities.size());
    }

    @Test
    void testFindVegetableByName() {
        Optional<ItemEntity> item = itemRepository.findByName("Red Capsicum");
        assertTrue(item.isPresent());
    }

    @Test
    void testFindById() {
        FruitEntity fruitEntity = new FruitEntity();
        fruitEntity.setName("Shimla Apple");
        fruitEntity.setCategory(1L);
        fruitEntity = itemRepository.save(fruitEntity);
        Optional<ItemEntity> item = itemRepository.findById(fruitEntity.getId());
        assertTrue(item.isPresent());
        assertEquals("Shimla Apple", item.get().getName());
    }

}