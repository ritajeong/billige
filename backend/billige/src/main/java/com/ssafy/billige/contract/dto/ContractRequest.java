package com.ssafy.billige.contract.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ContractRequest {
	private String token;
	private Long uid;
	private Long itemId;
	private LocalDate startDate;
	private LocalDate endDate;
	private int totalPrice;
}
