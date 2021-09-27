package com.ssafy.billige.user.service.impl;

import com.ssafy.billige.authentication.provider.RandomSaltProvider;
import com.ssafy.billige.user.domain.User;
import com.ssafy.billige.user.domain.UserStatus;
import com.ssafy.billige.user.dto.request.UserSignupRequest;
import com.ssafy.billige.user.repository.UserRepository;
import com.ssafy.billige.user.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

	@Autowired
 	private UserRepository userRepository;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Override
	public User getKakaoUser(User user) {
		return userRepository.findByUserTokenId(user.getUserTokenId()).orElseGet(() -> save(user));
	}

	@Override
	@Transactional
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public User getUser(long uid) {
		return userRepository.findByUid(uid)
			.orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));
	}

	@Override
	public boolean emailCheck(String userEmail){
		if(userRepository.existsByUserEmail(userEmail)){
			// 이메일 중복
			return false;
		}else {
			return true;
		}

	}

	@Override
	@Transactional(readOnly = false)
	public int signup(UserSignupRequest userSignupRequest){

		String salt = RandomSaltProvider.getNextSalt().toString();

		User user = User.builder()
				.userTokenId("B" + UUID.randomUUID().toString().substring(0,9))
				.userName(userSignupRequest.getUserName())
				.userNickname(userSignupRequest.getUserNickname())
				.userEmail(userSignupRequest.getUserEmail())
				.userPassword(passwordEncoder.encode(userSignupRequest.getUserPassword()+salt))
				.userSalt(salt)
				.is_deleted(UserStatus.N)
				.build();

		userRepository.save(user);

		return 1;
	}
}
