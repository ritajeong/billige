package com.ssafy.billige.contract.dto.response;

import java.time.LocalDate;

import com.ssafy.billige.item.domain.Item;
import com.ssafy.billige.user.domain.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContractSearchMyItemResponse {
	private User borrower;
	private Item item;
	private LocalDate startDate;
	private LocalDate endDate;
	private int totalPrice;
}
