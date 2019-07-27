package com.zerosolutions.warehousemanagementsystem.common.security.jwt.business.impl;

import com.zerosolutions.warehousemanagementsystem.common.security.jwt.business.JwtUserDetailsService;
import com.zerosolutions.warehousemanagementsystem.common.security.jwt.business.JwtUtil;
import com.zerosolutions.warehousemanagementsystem.common.security.jwt.business.api.JwtUserAuthentication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class JwtUserAuthenticationImplTest {

    @Autowired
    JwtUserAuthentication jwtUserAuthentication;

    @MockBean
    JwtUserDetailsService jwtUserDetailsService;

    @MockBean
    JwtUtil jwtUtil;

    @Test
    void authenticate() throws Exception {
        UserDetails userDetails = new User("sahil@gmail.com", "$2a$10$zZTZza8GvcZ8f27VuBp81OFxrOLgFrYEWiiNTXWd0CEk64loGqoe2", new ArrayList<>());
        when(jwtUserDetailsService.loadUserByUsername("sahil@gmail.com")).thenReturn(userDetails);
        when(jwtUtil.generateToken(userDetails)).thenReturn("token");
        String token = jwtUserAuthentication.authenticate("sahil@gmail.com", "sahil");
        assertNotNull(token);
    }
}