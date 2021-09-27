package com.ssafy.billige.authentication.filter;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssafy.billige.exception.InputNotFoundException;
import com.ssafy.billige.user.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;



public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());



    public CustomAuthenticationFilter(final AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(final HttpServletRequest request, final HttpServletResponse response){
        final UsernamePasswordAuthenticationToken authRequest;
        try {
            final User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            authRequest = new UsernamePasswordAuthenticationToken(user.getUserEmail(), user.getUserPassword());
            logger.info(user.getUserEmail() + " : login 시도");
        } catch (IOException exception){
            throw new InputNotFoundException();
        }
        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }



}


