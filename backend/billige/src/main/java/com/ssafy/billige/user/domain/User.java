package com.ssafy.billige.user.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ssafy.billige.contract.domain.Contract;
import com.ssafy.billige.item.domain.Item;

import lombok.*;

@Entity
@Data
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"userNickname", "userPassword", "userSalt", "userBli", "userSigunguCode", "userImage", "is_deleted"})
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uid;

	private String userTokenId;
	private String userName;
	private String userNickname;
	private String userEmail;
	private String userPassword;
	private String userSalt;
	private String userWallet;
	private int userBli;
	private String userAddress;
	private String userSigunguCode;
	private String userImage;

	@Enumerated(EnumType.STRING)
	private UserStatus is_deleted;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Item> items = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Contract> contracts = new ArrayList<>();
}
