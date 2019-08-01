package com.zerosolutions.warehousemanagementsystem.stock.business.impl;

import com.zerosolutions.warehousemanagementsystem.stock.business.api.ItemCategory;
import com.zerosolutions.warehousemanagementsystem.stock.data.entity.ItemCategoryEntity;
import com.zerosolutions.warehousemanagementsystem.stock.data.repository.ItemCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ItemCategoryImpl implements ItemCategory {

    @Autowired
    ItemCategoryRepository itemCategoryRepository;

    @Override
    public List<ItemCategoryEntity> findAll() {
        Iterable<ItemCategoryEntity> itemCategories = itemCategoryRepository.findAll();
        List<ItemCategoryEntity> itemCategoryList = new ArrayList<>();
        itemCategories.iterator().forEachRemaining(itemCategoryList::add);
        return itemCategoryList;
    }

    @Override
    public ItemCategoryEntity saveItemCategory(String name) {
        return itemCategoryRepository.save(new ItemCategoryEntity(name));
    }

    @Override
    public ItemCategoryEntity findItemCategoryById(Long id) {
        Optional<ItemCategoryEntity> itemCategoryEntityOptional = itemCategoryRepository.findById(id);
        return itemCategoryEntityOptional.orElseGet(ItemCategoryEntity::new);
    }

    @Override
    public ItemCategoryEntity findItemCategoryByName(String name) {
        Optional<ItemCategoryEntity> itemCategoryEntityOptional = itemCategoryRepository.findByName(name);
        return itemCategoryEntityOptional.orElseGet(ItemCategoryEntity::new);
    }
}
