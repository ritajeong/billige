package com.ssafy.billige.item.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.billige.item.domain.ActiveStatus;
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
	public void itemUpdate(ItemUpdateRequest itemRequest, Long uid) {
		Item item = getItem(itemRequest.getItemId());
		if (!item.getUser().getUid().equals(uid)) {
			throw new IllegalArgumentException("물품을 수정할 권한이 없습니다.");
		}
		item.updateItem(itemRequest);
	}

	@Override
	public void removeItem(Long itemId, Long uid) {
		Item item = getItem(itemId);
		if (!item.getUser().getUid().equals(uid)) {
			throw new IllegalArgumentException("물품을 삭제할 권한이 없습니다.");
		}
		itemRepository.deleteById(itemId);
	}

	@Override
	public void activeItem(Long itemId, Long uid) {
		Item item = getItem(itemId);
		if (!item.getUser().getUid().equals(uid)) {
			throw new IllegalArgumentException("물품을 비활성화할 권한이 없습니다.");
		}
		item.activeItem();
	}

	private Item getItem(Long itemId) {
		return itemRepository.findById(itemId)
			.orElseThrow(() -> new IllegalArgumentException("해당 제품이 존재하지 않습니다."));
	}
}
