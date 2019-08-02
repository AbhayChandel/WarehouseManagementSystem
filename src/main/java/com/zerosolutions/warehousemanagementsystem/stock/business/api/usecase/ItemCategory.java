package com.zerosolutions.warehousemanagementsystem.stock.business.api.usecase;

import com.zerosolutions.warehousemanagementsystem.stock.business.api.dto.ItemCategoryDto;

import java.util.List;

public interface ItemCategory {

    List<ItemCategoryDto> findAll();

    ItemCategoryDto saveItemCategory(String name);

    ItemCategoryDto findItemCategoryById(Long id);

    ItemCategoryDto findItemCategoryByName(String name);
}
