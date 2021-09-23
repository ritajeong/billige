package com.ssafy.billige.user.service.impl;

import com.ssafy.billige.user.domain.User;
import com.ssafy.billige.user.repository.UserRepository;
import com.ssafy.billige.user.service.UserService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Override
	public User getKakaoUser(User user) {
		return userRepository.findByTokenId(user.getTokenId()).orElseGet(() -> save(user));
	}

	@Override
	@Transactional
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public User getUser(long uid) {
		return userRepository.findById(uid)
			.orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));
	}

}
