package com.ssafy.billige.item.service;

import com.ssafy.billige.item.dto.request.ItemRegistryRequest;
import com.ssafy.billige.item.dto.request.ItemUpdateRequest;

public interface ItemCrudService {
	void saveItem(ItemRegistryRequest itemRegistryRequest);

	void itemUpdate(ItemUpdateRequest itemRequest);

	void removeItem(Long itemId);
}
