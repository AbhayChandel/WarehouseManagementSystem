package com.zerosolutions.warehousemanagementsystem.common.security.jwt.services.impl.rest;

import com.zerosolutions.warehousemanagementsystem.common.security.jwt.business.api.JwtUserAuthentication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(JwtUserAuthenticationServiceImpl.class)
class JwtJwtJwtUserAuthenticationServiceImplTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private JwtUserAuthentication jwtUserAuthentication;

    @Test
    void authenticate() {
    }
}