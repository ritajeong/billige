package com.ssafy.billige.item.service;

import java.util.List;

import com.ssafy.billige.item.domain.Item;
import com.ssafy.billige.item.dto.request.ItemRequest;

public interface ItemService {
	void saveItem(ItemRequest itemRequest);

	List<Item> myItems(long uid);
}
