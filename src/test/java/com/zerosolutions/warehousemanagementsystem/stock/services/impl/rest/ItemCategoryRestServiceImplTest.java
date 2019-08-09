package com.zerosolutions.warehousemanagementsystem.stock.services.impl.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zerosolutions.warehousemanagementsystem.common.error.advice.ErrorResult;
import com.zerosolutions.warehousemanagementsystem.common.security.jwt.filters.JwtValidationFilter;
import com.zerosolutions.warehousemanagementsystem.stock.business.api.dto.ItemCategoryDto;
import com.zerosolutions.warehousemanagementsystem.stock.business.api.usecase.ItemCategory;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcSecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
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
    public void findAll_HttpMethodNotAllowedError() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.put("/stock/itemcategory/all"))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    void findAll_ValidateJsonResponse() throws Exception {
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
    public void save_HttpMethodNotAllowedError() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.put("/stock/itemcategory/save"))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void save_MissingRequestObject() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/stock/itemcategory/save")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void save_NameParameterMissing() throws Exception {
        ItemCategoryDto itemCategoryDtoObject = new ItemCategoryDto();
        String requestBody = objectMapper.writeValueAsString(itemCategoryDtoObject);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post("/stock/itemcategory/save")
                .content(requestBody).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResult expectedErrorResponse = new ErrorResult("name", "must not be null");
        String expectedResponseBody = objectMapper.writeValueAsString(expectedErrorResponse);
        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        assertEquals(expectedResponseBody, actualResponseBody);
    }

    @Test
    public void save_ParametersPassedToBusinessLayer() throws Exception {
        ItemCategoryDto itemCategoryDto = new ItemCategoryDto();
        itemCategoryDto.setName("Kiwi");
        String requestBody = objectMapper.writeValueAsString(itemCategoryDto);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/stock/itemcategory/save")
                .content(requestBody).contentType(MediaType.APPLICATION_JSON));

        ArgumentCaptor<String> itemCategoryDtoArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(itemCategory, times(1)).save(itemCategoryDtoArgumentCaptor.capture());
        assertEquals("Kiwi", itemCategoryDtoArgumentCaptor.getValue());
    }

    @Test
    void save_ValidateJsonResponse() throws Exception {
        ItemCategoryDto itemCategoryDto = new ItemCategoryDto();
        itemCategoryDto.setName("Orange");
        itemCategoryDto.setId(15L);
        when(itemCategory.save(any())).thenReturn(itemCategoryDto);
        String requestBody = objectMapper.writeValueAsString(itemCategoryDto);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/stock/itemcategory/save")
                .content(requestBody).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.name").value("Orange"))
                .andExpect(jsonPath("$.id").value("15"));
    }

    @Test
    public void findById_HttpMethodNotAllowedError() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.put("/stock/itemcategory/find/id/10"))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void findById_IdParameterInvalid() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get("/stock/itemcategory/find/id/ab")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResult expectedErrorResponse = new ErrorResult("id", "value is not valid");
        String expectedResponseBody = objectMapper.writeValueAsString(expectedErrorResponse);
        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        assertEquals(expectedResponseBody, actualResponseBody);
    }

    @Test
    public void findById_ParametersPassedToBusinessLayer() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/stock/itemcategory/find/id/51"));

        ArgumentCaptor<Long> idArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(itemCategory, times(1)).findById(idArgumentCaptor.capture());
        assertEquals(Long.valueOf(51), idArgumentCaptor.getValue());
    }

    @Test
    void findById_ValidateJsonResponse() throws Exception {
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
    public void findByName_HttpMethodNotAllowedError() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.put("/stock/itemcategory/find/name/Apple"))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void findByName_InvalidNameParameter() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get("/stock/itemcategory/find/name/Apple-72")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResult expectedErrorResponse = new ErrorResult("name", "value is not valid");
        String expectedResponseBody = objectMapper.writeValueAsString(expectedErrorResponse);
        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        assertEquals(expectedResponseBody, actualResponseBody);
    }

    @Test
    public void findByName_ValidNameParameter() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/stock/itemcategory/find/name/Apple72")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void findByName_ParametersPassedToBusinessLayer() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/stock/itemcategory/find/name/Jackfruit"));

        ArgumentCaptor<String> nameArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(itemCategory, times(1)).findByName(nameArgumentCaptor.capture());
        assertEquals("Jackfruit", nameArgumentCaptor.getValue());
    }

    @Test
    void findByName_JsonResponse() throws Exception {
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