package com.ssafy.billige.user.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ssafy.billige.contract.domain.Contract;
import com.ssafy.billige.item.domain.Item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = {"password", "tokenId", "email"})
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

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Item> items = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Contract> contracts = new ArrayList<>();
}
