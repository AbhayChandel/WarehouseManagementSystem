package com.zerosolutions.warehousemanagementsystem.stock;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zerosolutions.warehousemanagementsystem.stock.business.api.dto.ItemCategoryDto;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.io.IOException;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ItemCategoryIT {

    @Autowired
    ObjectMapper objectMapper;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String jwtToken;

    @BeforeEach
    void getJwtToken() throws JSONException, IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject jwtRequestJson = new JSONObject();
        jwtRequestJson.put("username", "sahil@gmail.com");
        jwtRequestJson.put("password", "sahil");
        HttpEntity<String> request = new HttpEntity<>(jwtRequestJson.toString(), headers);
        ResponseEntity<String> responseEntity = this.restTemplate.postForEntity("/authenticate", request, String.class);
        JsonNode root = objectMapper.readTree(responseEntity.getBody());
        jwtToken = root.path("token").asText();
    }

    @Test
    void testFindAllItems() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(jwtToken);
        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);
        ResponseEntity<List<ItemCategoryDto>> responseEntity = restTemplate.exchange("/stock/itemcategory/all", HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<ItemCategoryDto>>() {
        });
        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        List<ItemCategoryDto> itemCategoryDtos = responseEntity.getBody();
        Assertions.assertTrue(itemCategoryDtos.size() >= 2);
    }

    @Test
    void testSavingItemCategory() throws JSONException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(jwtToken);
        JSONObject requestJson = new JSONObject();
        requestJson.put("name", "Orange");
        HttpEntity<String> request = new HttpEntity<>(requestJson.toString(), headers);
        ResponseEntity<ItemCategoryDto> responseEntity = this.restTemplate.postForEntity("/stock/itemcategory/save", request, ItemCategoryDto.class);
        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());

        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);
        ResponseEntity<ItemCategoryDto> itemCategoryEntityResponseEntity = restTemplate.exchange("/stock/itemcategory/find/name/Orange", HttpMethod.GET, httpEntity, ItemCategoryDto.class);
        ItemCategoryDto itemCategoryDto = itemCategoryEntityResponseEntity.getBody();
        Assertions.assertNotNull(itemCategoryDto);
        Assertions.assertEquals("Orange", itemCategoryDto.getName());
    }

    @Test
    void testFindItemCategoryById() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(jwtToken);
        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);
        ResponseEntity<ItemCategoryDto> responseEntity = restTemplate.exchange("/stock/itemcategory/find/id/2", HttpMethod.GET, httpEntity, ItemCategoryDto.class);
        ItemCategoryDto itemCategoryDto = responseEntity.getBody();
        Assertions.assertNotNull(itemCategoryDto);
        Assertions.assertEquals("Capsicum", itemCategoryDto.getName());
    }

    @Test
    void testFindItemCategoryByName() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(jwtToken);
        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);
        ResponseEntity<ItemCategoryDto> responseEntity = restTemplate.exchange("/stock/itemcategory/find/name/Capsicum", HttpMethod.GET, httpEntity, ItemCategoryDto.class);
        ItemCategoryDto itemCategoryDto = responseEntity.getBody();
        Assertions.assertNotNull(itemCategoryDto);
        Assertions.assertEquals(Long.valueOf(2), itemCategoryDto.getId());
    }
}
