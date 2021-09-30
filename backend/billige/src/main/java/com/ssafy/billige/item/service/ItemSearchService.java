package com.ssafy.billige.item.service;

import java.util.List;

import com.ssafy.billige.item.domain.Item;
import com.ssafy.billige.item.dto.response.ItemDetailResponse;
import com.ssafy.billige.item.dto.response.ItemResponse;

public interface ItemSearchService {
	List<Item> myItems(long uid);

	// 리스트로 뿌려줄 아이템 가져오기
	List<ItemResponse> getItems(int page, int userSigunguCode);

	ItemDetailResponse getItemDetails(long itemId, long uid);
}
