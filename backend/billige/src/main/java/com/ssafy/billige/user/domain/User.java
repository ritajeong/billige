package com.ssafy.billige.user.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@Builder
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uid;

	private Long tokenId;
	private String username;
	private String email;
	private String password;

	@Enumerated(EnumType.STRING)
	private UserStatus is_deleted;

	private String address;
	private String wallet;
	private int userSigunguCode;
}
