package com.zerosolutions.warehousemanagementsystem.stock.services.impl.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zerosolutions.warehousemanagementsystem.stock.business.api.ItemCategory;
import com.zerosolutions.warehousemanagementsystem.stock.data.entity.ItemCategoryEntity;
import com.zerosolutions.warehousemanagementsystem.stock.services.api.rest.ItemCategoryRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemCategoryRestServiceImpl implements ItemCategoryRestService {


    @Autowired
    ItemCategory itemCategory;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public ResponseEntity<List<ItemCategoryEntity>> findAllItemCategories() {
        return ResponseEntity.ok(itemCategory.findAll());
    }

    @Override
    public ResponseEntity<ItemCategoryEntity> save(ItemCategoryEntity itemCategoryEntity) {
        return ResponseEntity.ok(itemCategory.saveItemCategory(itemCategoryEntity.getName()));
    }

    @Override
    public ResponseEntity<ItemCategoryEntity> findById(Long id) {
        return ResponseEntity.ok(itemCategory.findItemCategoryById(id));
    }

    @Override
    public ResponseEntity<ItemCategoryEntity> findByName(String name) {
        return ResponseEntity.ok(itemCategory.findItemCategoryByName(name));
    }
}
