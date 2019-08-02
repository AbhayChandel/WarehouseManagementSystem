package com.zerosolutions.warehousemanagementsystem.common.security.jwt.services.api.rest;

import com.zerosolutions.warehousemanagementsystem.common.security.jwt.services.JwtRequest;
import com.zerosolutions.warehousemanagementsystem.common.security.jwt.services.JwtResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


public interface JwtUserAuthenticationRestService {

    @PostMapping(value = "/authenticate")
    ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody JwtRequest jwtRequest);
}
