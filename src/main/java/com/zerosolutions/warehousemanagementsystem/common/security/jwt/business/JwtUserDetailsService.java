package com.zerosolutions.warehousemanagementsystem.common.security.jwt.business;

import com.zerosolutions.warehousemanagementsystem.common.security.jwt.data.entity.UserAuthenticationEntity;
import com.zerosolutions.warehousemanagementsystem.common.security.jwt.data.repository.UserAuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserAuthenticationRepository userAuthenticationRepository;

    @Autowired
    JwtUserDetailsService(UserAuthenticationRepository userAuthenticationRepository) {
        this.userAuthenticationRepository = userAuthenticationRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<UserAuthenticationEntity> userAuthenticationEntity = userAuthenticationRepository.findByUsername(username);
        if (userAuthenticationEntity.isPresent()) {
            return new User(userAuthenticationEntity.get().getUsername(), userAuthenticationEntity.get().getPassword(),
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
