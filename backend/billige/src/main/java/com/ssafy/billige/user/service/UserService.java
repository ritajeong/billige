package com.ssafy.billige.user.service;

import com.ssafy.billige.user.domain.User;

public interface UserService {
	User getKakaoUser(User user);

	User save(User user);

	User getUser(long uid);
}
