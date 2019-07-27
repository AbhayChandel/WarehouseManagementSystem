package com.zerosolutions.warehousemanagementsystem.common.security.jwt.data.repository;

import com.zerosolutions.warehousemanagementsystem.common.security.jwt.data.entity.UserAuthenticationEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserAuthenticationRepository extends CrudRepository<UserAuthenticationEntity, Long> {

    Optional<UserAuthenticationEntity> findByUsername(String username);
}
