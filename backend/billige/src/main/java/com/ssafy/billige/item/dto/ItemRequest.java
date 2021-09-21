package com.ssafy.billige.item.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ItemRequest {
	private Long uid;
	private String itemname;
	private String category;
	private String description;
	private int price;
	private String position;
	private int itemSigunguCode;
}

