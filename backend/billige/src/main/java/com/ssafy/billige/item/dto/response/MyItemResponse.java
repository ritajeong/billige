package com.ssafy.billige.item.dto.response;

import java.time.LocalDateTime;

import com.ssafy.billige.item.domain.ActiveStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MyItemResponse extends ItemResponse{
	private ActiveStatus status;

	public MyItemResponse(ActiveStatus status) {
		this.status = status;
	}

	public MyItemResponse(Long itemId, String itemname, String position, int price, LocalDateTime modifiedTime,
		String image, ActiveStatus status) {
		super(itemId, itemname, position, price, modifiedTime, image);
		this.status = status;
	}
}
