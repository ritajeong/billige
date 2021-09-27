package com.ssafy.billige.user.service.impl;

import com.ssafy.billige.exception.UserNotFoundException;
import com.ssafy.billige.user.domain.CustomUserDetails;
import com.ssafy.billige.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUserEmail(username)
                .map(u -> new CustomUserDetails(u, Collections.singleton(new SimpleGrantedAuthority(u.getUserNickname())))).orElseThrow(() -> new UserNotFoundException(username));

    }
}
