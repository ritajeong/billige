package com.ssafy.billige.user.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.billige.authentication.provider.RandomSaltProvider;
import com.ssafy.billige.user.domain.User;
import com.ssafy.billige.user.domain.UserStatus;
import com.ssafy.billige.user.dto.request.UserSignupRequest;
import com.ssafy.billige.user.dto.response.UserEmailResponse;
import com.ssafy.billige.user.dto.response.UserProfileResponse;
import com.ssafy.billige.user.repository.UserRepository;
import com.ssafy.billige.user.service.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.UUID;

import static com.ssafy.billige.utils.StringUtils.*;

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
	public User getUser(String userEmail){
		return userRepository.findByUserEmail(userEmail)
				.orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));
	}

	@Override
	public boolean emailCheck(String userEmail){
		if(!userRepository.findByUserEmail(userEmail).isEmpty()){
			// 이메일 중복인 경우
			return false;
		}else return true;
	}

	@Override
	@Transactional(readOnly = false)
	public int signup(UserSignupRequest userSignupRequest){

		String salt = RandomSaltProvider.getNextSalt().toString();
		String tokenId = "B" + UUID.randomUUID().toString().substring(0,9);

		User user = User.builder()
				.userTokenId(tokenId)
				.userName(userSignupRequest.getUserName())
				.userNickname(tokenId)
				.userEmail(userSignupRequest.getUserEmail())
				.userPassword(passwordEncoder.encode(userSignupRequest.getUserPassword()+salt))
				.userSalt(salt)
				.is_deleted(UserStatus.N)
				.userAddress(DEFAULT_ADDRESS)
				.userSigunguCode(DEFAULT_CODE)
				.userImage("https://billige.s3.ap-northeast-2.amazonaws.com/profile/basicProfileImage.png")
				.build();

		userRepository.save(user);

		return 1;
	}

	@Override
	public String certificateEmail(String userEmail){

		// 인증번호 생성
		String key = certificationNumberGenerator();
		// 메일 생성
		UserEmailResponse mail = createEmail(userEmail, key);
		// 메일 전송
		mailSend(mail);

		return key;
	}

	@Autowired
	private JavaMailSender mailSender;
	@Override
	public void mailSend(UserEmailResponse emailDto){

		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(emailDto.getEmail());
		message.setFrom(FROM_ADDRESS);
		message.setSubject(emailDto.getTitle());
		message.setText(emailDto.getMessage());
		mailSender.send(message);
	}

	@Override
	public UserEmailResponse createEmail(String userEmail, String certificationNumber){

		UserEmailResponse userEmailResponse = new UserEmailResponse();
		userEmailResponse.setEmail(userEmail);
		userEmailResponse.setTitle("Billige 인증번호 안내 관련 메일 입니다.");
		userEmailResponse.setMessage("안녕하세요. Billige 인증번호 안내 관련 메일 입니다." + "\n" + "고객님의 인증번호는 " + certificationNumber + "입니다.");

		return userEmailResponse;
	}

	@Override
	public String certificationNumberGenerator(){

		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
				'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

		StringBuilder sb = new StringBuilder();
		int idx = 0;

		for (int i = 0; i < 6; i++) {
			idx = (int) (charSet.length * Math.random());
			sb.append(charSet[idx]);
		}
		return sb.toString();
	}

	@Override
	@Transactional
	public void modifyPassword(Map<String, String> modifyRequest){

		String requestEmail = modifyRequest.get("userEmail");
		String requestPassword = modifyRequest.get("userPassword");
		User user = getUser(requestEmail);

		String salt = RandomSaltProvider.getNextSalt().toString();
		user.setUserPassword(passwordEncoder.encode(requestPassword + salt));
		user.setUserSalt(salt);

		userRepository.save(user);
	}

	@Override
	@Transactional
	public void modifyProfile(String tokenEmail, String requestPassword, String imageUrl){

		User user = getUser(tokenEmail);
		if(requestPassword != null){
			String salt = RandomSaltProvider.getNextSalt().toString();
			user.setUserPassword(passwordEncoder.encode(requestPassword + salt));
			user.setUserSalt(salt);
		}
		if(imageUrl != null){
			user.setUserImage(imageUrl);
		}

		userRepository.save(user);
	}

	@Override
	@Transactional
	public void modifyAddress(String tokenEmail, Map<String, String> request){

		User user = getUser(tokenEmail);
		user.setUserAddress(request.get("userAddress"));
		user.setUserSigunguCode(request.get("userSigunguCode"));

		userRepository.save(user);
	}

	@Override
	@Transactional
	public void createWallet(String tokenEmail, String userWallet){

		User user = getUser(tokenEmail);
		if(user.getUserWallet() == null && userWallet != null){ // 지갑이 없는 회원이 지갑을 생성한 경우
			user.setUserWallet(userWallet);
			user.setUserBli(30);
		}



		userRepository.save(user);
	}

	@Override
	@Transactional
	public void chargeBli(String tokenEmail, int bli){

		User user = getUser(tokenEmail);
		int currentBli = user.getUserBli();
		int resultBli = currentBli + bli;
		user.setUserBli(resultBli);

		userRepository.save(user);
	}

	@Autowired
	ModelMapper modelMapper;
	@Override
	@Transactional
	public UserProfileResponse showProfile(String tokenEmail){

		User user = getUser(tokenEmail);
		UserProfileResponse result = modelMapper.map(user, UserProfileResponse.class);
		if(user.getUserWallet() != null){
			result.setExistWallet(true);
			result.setUserBli(user.getUserBli());
		}else {
			result.setExistWallet(false);
			result.setUserBli(0);
		}

		return result;
	}
}
