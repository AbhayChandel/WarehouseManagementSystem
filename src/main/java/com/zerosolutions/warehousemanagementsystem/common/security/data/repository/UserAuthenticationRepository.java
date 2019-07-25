package com.zerosolutions.warehousemanagementsystem.common.security.data.repository;

import com.zerosolutions.warehousemanagementsystem.common.security.data.entity.UserAuthenticationEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserAuthenticationRepository extends CrudRepository<UserAuthenticationEntity, Long> {
}
