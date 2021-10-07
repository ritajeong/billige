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
public class BorrowerResponse {
	private Long contract_id;
	private Long borrower_id;
	private String username;
	private String userImage;
	private Long itemId;
	private String position;
	private LocalDate startDate;
	private LocalDate endDate;
}
