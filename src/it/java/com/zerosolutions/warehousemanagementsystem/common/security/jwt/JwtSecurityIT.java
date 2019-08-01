package com.zerosolutions.warehousemanagementsystem.common.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JwtSecurityIT {

    @Autowired
    ObjectMapper objectMapper;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testTokenGeneration() throws JSONException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject jwtRequestJson = new JSONObject();
        jwtRequestJson.put("username", "sahil@gmail.com");
        jwtRequestJson.put("password", "sahil");
        HttpEntity<String> request = new HttpEntity<>(jwtRequestJson.toString(), headers);
        ResponseEntity<String> responseEntity = this.restTemplate.postForEntity("/authenticate", request, String.class);
        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    void testAccessSecuredResourceWithoutAccessToken() {
        ResponseEntity<String> responseEntity = this.restTemplate.getForEntity("/stock/itemcategory/all", String.class);
        Assertions.assertEquals(401, responseEntity.getStatusCodeValue());
    }
}
