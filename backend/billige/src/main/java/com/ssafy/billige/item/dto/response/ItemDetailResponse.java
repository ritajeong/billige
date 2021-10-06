package com.ssafy.billige.item.dto.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDetailResponse {
	private Long itemId;
	private String itemname;
	private String category;
	private String description;
	private String position;
	private int price;
	private LocalDateTime modifiedTime;
	private Owner owner;
	private boolean isBookmark;
	private List<String> imgSrc = new ArrayList<>();

	@Data
	@Builder
	public static class Owner {
		private Long uid;
		private String username;
		private String address;
		private String image;
		private String wallet;
	}
}
