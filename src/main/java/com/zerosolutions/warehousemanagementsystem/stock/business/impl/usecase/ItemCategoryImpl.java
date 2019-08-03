package com.zerosolutions.warehousemanagementsystem.stock.business.impl.usecase;

import com.zerosolutions.warehousemanagementsystem.stock.business.api.dto.ItemCategoryDto;
import com.zerosolutions.warehousemanagementsystem.stock.business.api.dto.mapper.ItemCategoryMapper;
import com.zerosolutions.warehousemanagementsystem.stock.business.api.usecase.ItemCategory;
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

    @Autowired
    ItemCategoryMapper itemCategoryMapper;

    @Override
    public List<ItemCategoryDto> findAll() {
        Iterable<ItemCategoryEntity> itemCategories = itemCategoryRepository.findAll();
        List<ItemCategoryEntity> itemCategoryList = new ArrayList<>();
        itemCategories.iterator().forEachRemaining(itemCategoryList::add);
        return itemCategoryMapper.toDtos(itemCategoryList);
    }

    @Override
    public ItemCategoryDto save(String name) {
        ItemCategoryEntity itemCategoryEntity = itemCategoryRepository.save(new ItemCategoryEntity(name));
        return itemCategoryMapper.toDto(itemCategoryEntity);
    }

    @Override
    public ItemCategoryDto findById(Long id) {
        Optional<ItemCategoryEntity> itemCategoryEntityOptional = itemCategoryRepository.findById(id);
        ItemCategoryEntity itemCategoryEntity = itemCategoryEntityOptional.orElseGet(ItemCategoryEntity::new);
        return itemCategoryMapper.toDto(itemCategoryEntity);
    }

    @Override
    public ItemCategoryDto findByName(String name) {
        Optional<ItemCategoryEntity> itemCategoryEntityOptional = itemCategoryRepository.findByName(name);
        ItemCategoryEntity itemCategoryEntity = itemCategoryEntityOptional.orElseGet(ItemCategoryEntity::new);
        return itemCategoryMapper.toDto(itemCategoryEntity);
    }
}
