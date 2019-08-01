package com.zerosolutions.warehousemanagementsystem.stock.business.api;

import com.zerosolutions.warehousemanagementsystem.stock.data.entity.ItemCategoryEntity;

import java.util.List;

public interface ItemCategory {

    List<ItemCategoryEntity> findAll();

    ItemCategoryEntity saveItemCategory(String name);

    ItemCategoryEntity findItemCategoryById(Long id);

    ItemCategoryEntity findItemCategoryByName(String name);
}
