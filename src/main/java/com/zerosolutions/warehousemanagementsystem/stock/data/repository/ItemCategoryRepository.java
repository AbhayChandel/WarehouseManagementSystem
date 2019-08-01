package com.zerosolutions.warehousemanagementsystem.stock.data.repository;

import com.zerosolutions.warehousemanagementsystem.stock.data.entity.ItemCategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemCategoryRepository extends CrudRepository<ItemCategoryEntity, Long> {
    Optional<ItemCategoryEntity> findByName(String name);
}
