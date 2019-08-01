package com.zerosolutions.warehousemanagementsystem.stock;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zerosolutions.warehousemanagementsystem.stock.data.entity.ItemCategoryEntity;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.io.IOException;

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
        ResponseEntity<String> responseEntity = restTemplate.exchange("/stock/itemcategory/all", HttpMethod.GET, httpEntity, String.class);
        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        JsonNode root = objectMapper.readTree(responseEntity.getBody());
        Assertions.assertTrue(root.size() >= 2);
    }

    @Test
    void testSavingItemCategory() throws JSONException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(jwtToken);
        JSONObject requestJson = new JSONObject();
        requestJson.put("name", "Orange");
        HttpEntity<String> request = new HttpEntity<>(requestJson.toString(), headers);
        ResponseEntity<ItemCategoryEntity> responseEntity = this.restTemplate.postForEntity("/stock/itemcategory/save", request, ItemCategoryEntity.class);
        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());

        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);
        ResponseEntity<ItemCategoryEntity> itemCategoryEntityResponseEntity = restTemplate.exchange("/stock/itemcategory/find/name/Orange", HttpMethod.GET, httpEntity, ItemCategoryEntity.class);
        ItemCategoryEntity itemCategoryEntity = itemCategoryEntityResponseEntity.getBody();
        Assertions.assertNotNull(itemCategoryEntity);
        Assertions.assertEquals("Orange", itemCategoryEntity.getName());
    }

    @Test
    void testFindItemCategoryById() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(jwtToken);
        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);
        ResponseEntity<ItemCategoryEntity> responseEntity = restTemplate.exchange("/stock/itemcategory/find/id/2", HttpMethod.GET, httpEntity, ItemCategoryEntity.class);
        ItemCategoryEntity itemCategoryEntity = responseEntity.getBody();
        Assertions.assertNotNull(itemCategoryEntity);
        Assertions.assertEquals("Capsicum", itemCategoryEntity.getName());
    }

    @Test
    void testFindItemCategoryByName() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(jwtToken);
        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);
        ResponseEntity<ItemCategoryEntity> responseEntity = restTemplate.exchange("/stock/itemcategory/find/name/Capsicum", HttpMethod.GET, httpEntity, ItemCategoryEntity.class);
        ItemCategoryEntity itemCategoryEntity = responseEntity.getBody();
        Assertions.assertNotNull(itemCategoryEntity);
        Assertions.assertEquals(Long.valueOf(2), itemCategoryEntity.getId());
    }
}
