package com.ssafy.billige.user.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.billige.contract.domain.Contract;
import com.ssafy.billige.item.domain.Item;

import lombok.*;

@Entity
@Data
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uid;

	private String userTokenId;
	private String userName;
	@JsonIgnore
	private String userNickname;
	private String userEmail;
	private String userPassword;
	@JsonIgnore
	private String userSalt;

	@JsonIgnore
	@Enumerated(EnumType.STRING)
	private UserStatus is_deleted;
	@JsonIgnore
	private String userAddress;
	@JsonIgnore
	private String userWallet;
	@JsonIgnore
	private int userSigunguCode;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Item> items = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Contract> contracts = new ArrayList<>();
}
