package com.ssafy.billige.contract.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.billige.item.domain.Item;
import com.ssafy.billige.user.domain.User;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Contract {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long contractId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uid")
	private User user;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	private Item item;

	private Long ownerId;

	private LocalDate startDate;
	private LocalDate endDate;

	private int totalPrice;
}
