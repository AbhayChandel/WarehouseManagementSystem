package com.zerosolutions.warehousemanagementsystem.stock.data.repository;

import com.zerosolutions.warehousemanagementsystem.stock.data.entity.ItemEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends CrudRepository<ItemEntity, Long> {
    List<ItemEntity> findByCategory(Long categoryId);

    Optional<ItemEntity> findByName(String name);
}
