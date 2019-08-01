package com.zerosolutions.warehousemanagementsystem.stock.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "item_category")
public class ItemCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_category_generator")
    @SequenceGenerator(name = "item_category_generator", sequenceName = "item_category_seq", allocationSize = 1)
    private Long id;
    private String name;

    public ItemCategoryEntity() {
    }

    public ItemCategoryEntity(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
