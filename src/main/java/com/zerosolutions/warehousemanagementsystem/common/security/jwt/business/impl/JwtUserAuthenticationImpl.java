package com.zerosolutions.warehousemanagementsystem.common.security.jwt.business.impl;

import com.zerosolutions.warehousemanagementsystem.common.security.jwt.business.JwtUserDetailsService;
import com.zerosolutions.warehousemanagementsystem.common.security.jwt.business.JwtUtil;
import com.zerosolutions.warehousemanagementsystem.common.security.jwt.business.api.JwtUserAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtUserAuthenticationImpl implements JwtUserAuthentication {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    public JwtUserAuthenticationImpl(AuthenticationManager authenticationManager, JwtUtil jwtUtil, JwtUserDetailsService jwtUserDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.jwtUserDetailsService = jwtUserDetailsService;
    }


    @Override
    public String authenticate(String username, String password) throws Exception {
        usernamePasswordAuthenticate(username, password);

        final UserDetails userDetails = jwtUserDetailsService
                .loadUserByUsername(username);

        return jwtUtil.generateToken(userDetails);
    }

    private void usernamePasswordAuthenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
