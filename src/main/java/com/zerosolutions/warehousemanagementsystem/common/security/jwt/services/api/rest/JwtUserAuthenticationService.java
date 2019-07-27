package com.zerosolutions.warehousemanagementsystem.common.security.jwt.services.api.rest;

import com.zerosolutions.warehousemanagementsystem.common.security.jwt.services.JwtRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


public interface JwtUserAuthenticationService {

    @PostMapping(value = "/authenticate")
    ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest jwtRequest) throws Exception;
}
