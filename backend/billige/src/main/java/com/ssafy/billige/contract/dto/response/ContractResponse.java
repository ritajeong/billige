package com.ssafy.billige.contract.dto.response;

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
public class ContractResponse {
	private Long contractId;
	private String itemname;
	private String position;
	private String itemImage;
	private Long borrowerId;
	private String borrowerName;
	private LocalDate startDate;
	private LocalDate endDate;
	private int price;
	private int totalPrice;
}
