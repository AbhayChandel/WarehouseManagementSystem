package com.zerosolutions.warehousemanagementsystem.stock.services.api.rest;

import com.zerosolutions.warehousemanagementsystem.stock.data.entity.ItemCategoryEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/stock/itemcategory")
public interface ItemCategoryRestService {

    @GetMapping(value = "/all")
    ResponseEntity<List<ItemCategoryEntity>> findAllItemCategories();

    @PostMapping(value = "/save")
    ResponseEntity<ItemCategoryEntity> save(@RequestBody ItemCategoryEntity itemCategoryEntity);

    @GetMapping(value = "/find/id/{id}")
    ResponseEntity<ItemCategoryEntity> findById(@PathVariable("id") Long id);

    @GetMapping(value = "/find/name/{name}")
    ResponseEntity<ItemCategoryEntity> findByName(@PathVariable("name") String name);
}
