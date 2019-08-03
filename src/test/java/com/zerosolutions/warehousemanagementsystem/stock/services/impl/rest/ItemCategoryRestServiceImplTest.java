package com.zerosolutions.warehousemanagementsystem.stock.services.impl.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zerosolutions.warehousemanagementsystem.common.security.jwt.filters.JwtValidationFilter;
import com.zerosolutions.warehousemanagementsystem.stock.business.api.dto.ItemCategoryDto;
import com.zerosolutions.warehousemanagementsystem.stock.business.api.usecase.ItemCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcSecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = ItemCategoryRestServiceImpl.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {WebSecurityConfigurer.class, JwtValidationFilter.class}),
        excludeAutoConfiguration = MockMvcSecurityAutoConfiguration.class)
class ItemCategoryRestServiceImplTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ItemCategory itemCategory;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void testFindAllHttpMethodNotAllowedError() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.put("/stock/itemcategory/all"))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    void testFindAllJsonResponse() throws Exception {
        ItemCategoryDto itemCategoryDtoApple = new ItemCategoryDto();
        itemCategoryDtoApple.setName("Apple");
        itemCategoryDtoApple.setId(15L);
        ItemCategoryDto itemCategoryDtoOrange = new ItemCategoryDto();
        itemCategoryDtoOrange.setName("Orange");
        itemCategoryDtoOrange.setId(16L);
        List<ItemCategoryDto> itemCategoryDtos = new ArrayList<>();
        itemCategoryDtos.add(itemCategoryDtoApple);
        itemCategoryDtos.add(itemCategoryDtoOrange);
        when(itemCategory.findAll()).thenReturn(itemCategoryDtos);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/stock/itemcategory/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void testSaveHttpMethodNotAllowedError() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.put("/stock/itemcategory/all"))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void testSaveHttpMethodBadRequestError() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/stock/itemcategory/save")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    /*
    This test should fail after implementation of issue #31
     */
    void testSaveJsonResponse() throws Exception {
        ItemCategoryDto itemCategoryDto = new ItemCategoryDto();
        itemCategoryDto.setName("Orange");
        itemCategoryDto.setId(15L);
        when(itemCategory.save(any())).thenReturn(itemCategoryDto);
        ItemCategoryDto itemCategoryDtoObject = new ItemCategoryDto();
        String requestBody = objectMapper.writeValueAsString(itemCategoryDto);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/stock/itemcategory/save")
                .content(requestBody).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.name").value("Orange"))
                .andExpect(jsonPath("$.id").value("15"));
    }

    @Test
    public void testFindByIdHttpMethodNotAllowedError() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.put("/stock/itemcategory/all"))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    void testFindByIdJsonResponse() throws Exception {
        ItemCategoryDto itemCategoryDto = new ItemCategoryDto();
        itemCategoryDto.setName("Apple");
        itemCategoryDto.setId(15L);
        when(itemCategory.findById(15L)).thenReturn(itemCategoryDto);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/stock/itemcategory/find/id/15"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.id").value("15"))
                .andExpect(jsonPath("$.name").value("Apple"));
    }

    @Test
    public void testFindByNameHttpMethodNotAllowedError() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.put("/stock/itemcategory/all"))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    void testFindByNameJsonResponse() throws Exception {
        ItemCategoryDto itemCategoryDto = new ItemCategoryDto();
        itemCategoryDto.setName("Apple");
        itemCategoryDto.setId(35L);
        when(itemCategory.findByName("Apple")).thenReturn(itemCategoryDto);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/stock/itemcategory/find/name/Apple"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.id").value("35"));
    }
}