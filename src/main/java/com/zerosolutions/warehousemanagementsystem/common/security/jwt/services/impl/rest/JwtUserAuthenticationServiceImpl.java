package com.zerosolutions.warehousemanagementsystem.common.security.jwt.services.impl.rest;

import com.zerosolutions.warehousemanagementsystem.common.security.jwt.business.api.JwtUserAuthentication;
import com.zerosolutions.warehousemanagementsystem.common.security.jwt.services.JwtRequest;
import com.zerosolutions.warehousemanagementsystem.common.security.jwt.services.JwtResponse;
import com.zerosolutions.warehousemanagementsystem.common.security.jwt.services.api.rest.JwtUserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtUserAuthenticationServiceImpl implements JwtUserAuthenticationService {

    private final JwtUserAuthentication jwtUserAuthentication;

    @Autowired
    JwtUserAuthenticationServiceImpl(JwtUserAuthentication jwtUserAuthentication) {
        this.jwtUserAuthentication = jwtUserAuthentication;
    }

    @Override
    public ResponseEntity<?> createAuthenticationToken(JwtRequest jwtRequest) throws Exception {
        return ResponseEntity.ok(new JwtResponse(jwtUserAuthentication.authenticate(jwtRequest.getUsername(), jwtRequest.getPassword())));
    }
}