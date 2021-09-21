package com.ssafy.billige.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.billige.user.service.KakaoLoginService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class KakaoLoginController {

	private final KakaoLoginService kakaoLoginService;

	@GetMapping("/kakao")
	public ResponseEntity<?> kakaoLogin(@RequestParam("code") String code) {
		return ResponseEntity.ok().body(kakaoLoginService.kakaoLogin(code));
	}
}
