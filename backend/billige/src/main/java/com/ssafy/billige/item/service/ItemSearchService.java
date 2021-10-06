package com.ssafy.billige.item.service;

import java.util.List;

import com.ssafy.billige.item.domain.Item;
import com.ssafy.billige.item.dto.response.ItemDetailResponse;
import com.ssafy.billige.item.dto.response.ItemResponse;
import com.ssafy.billige.item.dto.response.MyItemResponse;

public interface ItemSearchService {
	List<MyItemResponse> myItems(long uid);

	// 리스트로 뿌려줄 아이템 가져오기
	List<ItemResponse> getItems(int userSigunguCode);

	ItemDetailResponse getItemDetails(long itemId, long uid);
}
