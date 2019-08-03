package com.zerosolutions.warehousemanagementsystem.stock.business.api.usecase;

import com.zerosolutions.warehousemanagementsystem.stock.business.api.dto.ItemCategoryDto;

import java.util.List;

public interface ItemCategory {

    List<ItemCategoryDto> findAll();

    ItemCategoryDto save(String name);

    ItemCategoryDto findById(Long id);

    ItemCategoryDto findByName(String name);
}
