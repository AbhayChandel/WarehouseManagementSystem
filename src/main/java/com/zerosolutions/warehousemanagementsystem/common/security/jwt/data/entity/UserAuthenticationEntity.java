package com.zerosolutions.warehousemanagementsystem.common.security.jwt.data.entity;

import com.zerosolutions.warehousemanagementsystem.common.data.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_authentication")
public class UserAuthenticationEntity extends BaseEntity {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
