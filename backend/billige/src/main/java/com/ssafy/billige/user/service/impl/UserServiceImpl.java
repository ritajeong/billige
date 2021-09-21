package com.ssafy.billige.user.service.impl;

import java.util.Optional;

import com.ssafy.billige.user.domain.User;
import com.ssafy.billige.user.repository.UserRepository;
import com.ssafy.billige.user.service.UserService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Override
	public User getKakaoUser(User user) {
		return userRepository.findByTokenId(user.getTokenId()).orElseGet(() -> save(user));
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

}
