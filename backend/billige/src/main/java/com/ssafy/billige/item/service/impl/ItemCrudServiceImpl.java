package com.ssafy.billige.item.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.billige.item.domain.Item;
import com.ssafy.billige.item.dto.request.ItemRegistryRequest;
import com.ssafy.billige.item.dto.request.ItemUpdateRequest;
import com.ssafy.billige.item.repository.ItemRepository;
import com.ssafy.billige.item.service.ItemCrudService;
import com.ssafy.billige.user.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemCrudServiceImpl implements ItemCrudService {

	private final ItemRepository itemRepository;
	private final UserService userService;
	private final ModelMapper modelMapper;

	@Override
	public void saveItem(ItemRegistryRequest itemRegistryRequest) {
		Item item = modelMapper.map(itemRegistryRequest, Item.class);
		item.setUser(userService.getUser(itemRegistryRequest.getUid()));
		itemRepository.save(item);
	}

	@Override
	public void itemUpdate(ItemUpdateRequest itemRequest) {
		Item item = itemRepository.findById(itemRequest.getItemId())
			.orElseThrow(() -> new IllegalArgumentException("해당 제품이 존재하지 않습니다."));
		item.updateItem(item, itemRequest);
	}
}
