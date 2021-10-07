package com.ssafy.billige.user.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.ssafy.billige.user.domain.User;
import com.ssafy.billige.user.domain.UserStatus;
import com.ssafy.billige.user.service.KakaoLoginService;
import com.ssafy.billige.user.service.UserService;
import com.ssafy.billige.utils.TokenUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KakaoLoginServiceImpl implements KakaoLoginService {

	private final String tokenUrl = "https://kauth.kakao.com/oauth/token";
	private final String userUrl = "https://kapi.kakao.com/v2/user/me";
	// private final String redirectUri = "http://localhost:8080/api/login/kakao";
	// private final String redirectUri = "http://j5a401.p.ssafy.com/api/login/kakao";
	private final String redirectUri = "http://j5a401.p.ssafy.com";
	private final String clientId = "5d949b6a969e63a10e108f1ddf722cd3";
	private final String grantType = "authorization_code";

	private final UserService userService;

	@Override
	@Transactional
	public String kakaoLogin(String code) {

		HttpURLConnection conn = null;
		URL url = null;
		String accessToken = "";

		try {
			url = new URL(tokenUrl);
			conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter((conn.getOutputStream())));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=").append(grantType);
			sb.append("&client_id=").append(clientId);
			sb.append("&redirect_uri=").append(redirectUri);
			sb.append("&code=").append(code);
			bw.write(sb.toString());
			bw.flush();

			JsonElement element = getJsonElement(conn);
			accessToken = getJsonElementValue(element, "access_token").getAsString();
			conn.disconnect();

			url = new URL(userUrl);
			conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.setDoOutput(true);
			conn.setRequestProperty("Authorization", "Bearer " + accessToken);

			element = getJsonElement(conn);
			JsonElement kakaoAccount = getJsonElementValue(element, "kakao_account");
			String tokenId = getJsonElementValue(element, "id").getAsString();
			JsonElement properties = getJsonElementValue(element, "properties");
			String nickname = getJsonElementValue(properties, "nickname").getAsString();
			String image = getJsonElementValue(properties, "profile_image").getAsString();
			String email = getJsonElementValue(kakaoAccount, "email").getAsString();

			User user = User.builder()
				.userTokenId(tokenId)
				.userName(nickname)
				.userEmail(email)
				.userImage(image)
				.is_deleted(UserStatus.N)
				.build();

			User kakaoUser = userService.getKakaoUser(user);
			kakaoUser.setUserImage(image);

			return TokenUtils.generateJwtToken(kakaoUser);
		} catch (IOException e) {
			e.printStackTrace();
		}
		throw new IllegalArgumentException("카카오 로그인에 실패했습니다.");
	}

	private JsonElement getJsonElementValue(JsonElement element, String value) {
		return element.getAsJsonObject().get(value);
	}

	private JsonElement getJsonElement(HttpURLConnection conn) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line = "";
		StringBuilder result = new StringBuilder();

		while ((line = br.readLine()) != null) {
			result.append(line);
		}

		JsonParser parser = new JsonParser();
		return parser.parse(result.toString());
	}
}
