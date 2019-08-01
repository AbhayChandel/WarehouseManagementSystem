package com.zerosolutions.warehousemanagementsystem.stock.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "item")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_id_generator")
    @SequenceGenerator(name = "item_id_generator", sequenceName = "item_seq", allocationSize = 1)
    protected Long id;

    private String name;


    private Long category;

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

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }
}
