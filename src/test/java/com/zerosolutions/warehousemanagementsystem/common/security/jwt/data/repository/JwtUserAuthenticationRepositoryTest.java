package com.zerosolutions.warehousemanagementsystem.common.security.jwt.data.repository;

import com.zerosolutions.warehousemanagementsystem.common.security.jwt.data.entity.UserAuthenticationEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class JwtUserAuthenticationRepositoryTest {

    @Autowired
    UserAuthenticationRepository userAuthenticationRepository;

    @Test
    public void testRetrievingUserAuthenticationEntityById() {
        Optional<UserAuthenticationEntity> userAuthenticationEntity = userAuthenticationRepository.findById(1L);
        assertTrue(userAuthenticationEntity.isPresent());
    }

    @Test
    public void testRetrievingUserAuthenticationEntityByUsername() {
        Optional<UserAuthenticationEntity> userAuthenticationEntity = userAuthenticationRepository.findByUsername("sahil@gmail.com");
        assertTrue(userAuthenticationEntity.isPresent());
    }

}