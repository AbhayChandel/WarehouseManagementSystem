package com.zerosolutions.warehousemanagementsystem.stock.services.api.rest;

import com.zerosolutions.warehousemanagementsystem.stock.business.api.dto.ItemCategoryDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;

@RequestMapping("/stock/itemcategory")
@Validated
public interface ItemCategoryRestService {

    @GetMapping(value = "/all")
    ResponseEntity<List<ItemCategoryDto>> findAll();

    @PostMapping(value = "/save")
    ResponseEntity<ItemCategoryDto> save(@RequestBody @Valid ItemCategoryDto itemCategoryDto);

    @GetMapping(value = "/find/id/{id}")
    ResponseEntity<ItemCategoryDto> findById(@PathVariable("id") Long id);

    @GetMapping(value = "/find/name/{name}")
    ResponseEntity<ItemCategoryDto> findByName(@PathVariable("name") @Pattern(regexp = "^[a-zA-Z0-9]+$") String name);
}
