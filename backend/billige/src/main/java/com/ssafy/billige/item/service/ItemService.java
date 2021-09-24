package com.ssafy.billige.item.service;

import java.util.List;

import com.ssafy.billige.item.dto.request.ItemRequest;
import com.ssafy.billige.item.dto.response.ItemResponse;

public interface ItemService {
	void saveItem(ItemRequest itemRequest);

	List<ItemResponse> myItems(long uid);
}
