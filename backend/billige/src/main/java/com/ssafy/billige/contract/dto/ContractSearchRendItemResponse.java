package com.ssafy.billige.contract.dto;

import java.time.LocalDate;

import com.ssafy.billige.item.domain.Item;

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
public class ContractSearchRendItemResponse {
	private Item item;
	private LocalDate startDate;
	private LocalDate endDate;
}
