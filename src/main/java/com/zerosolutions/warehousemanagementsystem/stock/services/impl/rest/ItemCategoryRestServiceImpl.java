package com.zerosolutions.warehousemanagementsystem.stock.services.impl.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zerosolutions.warehousemanagementsystem.stock.business.api.dto.ItemCategoryDto;
import com.zerosolutions.warehousemanagementsystem.stock.business.api.usecase.ItemCategory;
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
    public ResponseEntity<List<ItemCategoryDto>> findAll() {
        return ResponseEntity.ok(itemCategory.findAll());
    }

    @Override
    public ResponseEntity<ItemCategoryDto> save(ItemCategoryDto itemCategoryDto) {
        return ResponseEntity.ok(itemCategory.save(itemCategoryDto.getName()));
    }

    @Override
    public ResponseEntity<ItemCategoryDto> findById(Long id) {
        return ResponseEntity.ok(itemCategory.findById(id));
    }

    @Override
    public ResponseEntity<ItemCategoryDto> findByName(String name) {
        return ResponseEntity.ok(itemCategory.findByName(name));
    }
}
