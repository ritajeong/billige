package com.ssafy.billige.user.service;

import com.ssafy.billige.user.domain.User;
import com.ssafy.billige.user.dto.request.UserSignupRequest;
import com.ssafy.billige.user.dto.response.UserEmailResponse;

import java.util.Map;

public interface UserService {
	User getKakaoUser(User user);

	User save(User user);

	User getUser(long uid);

	public User getUser(String userEmail);

	boolean emailCheck(String userEmail);

	int signup(UserSignupRequest userSignupRequest);

	public String certificateEmail(String userEmail);

	public void mailSend(UserEmailResponse emailDto);

	public UserEmailResponse createEmail(String userEmail, String certificationNumber);

	public String certificationNumberGenerator();

	public void modifyPassword(Map<String, String> modifyRequest);
}
