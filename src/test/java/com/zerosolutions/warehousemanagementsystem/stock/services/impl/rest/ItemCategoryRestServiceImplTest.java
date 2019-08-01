package com.zerosolutions.warehousemanagementsystem.stock.services.impl.rest;

import com.zerosolutions.warehousemanagementsystem.stock.business.api.ItemCategory;
import com.zerosolutions.warehousemanagementsystem.stock.data.entity.ItemCategoryEntity;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = ItemCategoryRestServiceImpl.class)
@Disabled("Dependent on issue #18")
class ItemCategoryRestServiceImplTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ItemCategory itemCategory;

    @Test
    void testFindById() throws Exception {
        ItemCategoryEntity itemCategoryEntity = new ItemCategoryEntity("Apple");
        itemCategoryEntity.setId(15L);
        when(itemCategory.findItemCategoryById(15L)).thenReturn(itemCategoryEntity);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/find/id/15"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.id").value("15"))
                .andExpect(jsonPath("$.name").value("Apple"));
    }
}