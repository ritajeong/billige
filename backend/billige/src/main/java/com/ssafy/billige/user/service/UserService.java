package com.ssafy.billige.user.service;

import com.ssafy.billige.user.domain.User;
import com.ssafy.billige.user.dto.request.UserSignupRequest;

public interface UserService {
	User getKakaoUser(User user);

	User save(User user);

	User getUser(long uid);

	boolean emailCheck(String userEmail);

	int signup(UserSignupRequest userSignupRequest);
}
