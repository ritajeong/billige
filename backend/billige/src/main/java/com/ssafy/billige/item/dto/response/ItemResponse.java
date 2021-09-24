package com.ssafy.billige.item.dto.response;

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
public class ItemResponse {
	private Long uid;
	private String itemname;
	private String category;
	private String description;
	private int price;
	private String position;
	private int itemSigunguCode;
}
