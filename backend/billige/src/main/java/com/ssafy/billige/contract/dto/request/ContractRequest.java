package com.ssafy.billige.contract.dto.request;

import java.time.LocalDate;

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
public class ContractRequest {
	private String token;
	private Long uid;
	private Long ownerId;
	private Long itemId;
	private LocalDate startDate;
	private LocalDate endDate;
	private int totalPrice;
}
