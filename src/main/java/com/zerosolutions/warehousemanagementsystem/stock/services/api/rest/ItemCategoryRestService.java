package com.zerosolutions.warehousemanagementsystem.stock.services.api.rest;

import com.zerosolutions.warehousemanagementsystem.stock.business.api.dto.ItemCategoryDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/stock/itemcategory")
public interface ItemCategoryRestService {

    @GetMapping(value = "/all")
    ResponseEntity<List<ItemCategoryDto>> findAllItemCategories();

    @PostMapping(value = "/save")
    ResponseEntity<ItemCategoryDto> save(@RequestBody ItemCategoryDto itemCategoryDto);

    @GetMapping(value = "/find/id/{id}")
    ResponseEntity<ItemCategoryDto> findById(@PathVariable("id") Long id);

    @GetMapping(value = "/find/name/{name}")
    ResponseEntity<ItemCategoryDto> findByName(@PathVariable("name") String name);
}
