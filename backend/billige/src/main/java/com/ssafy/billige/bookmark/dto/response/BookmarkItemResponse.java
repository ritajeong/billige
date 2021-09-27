package com.ssafy.billige.bookmark.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookmarkItemResponse {
	private Long itemId;
	private String itemname;
	private String position;
	private int price;
	private String image;
}
