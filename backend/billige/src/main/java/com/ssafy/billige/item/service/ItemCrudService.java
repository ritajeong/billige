package com.ssafy.billige.item.service;

import com.ssafy.billige.item.domain.Item;
import com.ssafy.billige.item.dto.request.ItemRegistryRequest;
import com.ssafy.billige.item.dto.request.ItemUpdateRequest;

public interface ItemCrudService {
	Item saveItem(ItemRegistryRequest itemRegistryRequest);

	void itemUpdate(ItemUpdateRequest itemRequest, Long uid);

	void removeItem(Long itemId, Long uid);

	void activeItem(Long itemId, Long uid);
}
