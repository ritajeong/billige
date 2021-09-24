package com.ssafy.billige.item.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.billige.item.domain.Item;
import com.ssafy.billige.item.dto.request.ItemRequest;
import com.ssafy.billige.item.dto.response.ItemResponse;
import com.ssafy.billige.item.repository.ItemRepository;
import com.ssafy.billige.item.service.ItemService;
import com.ssafy.billige.user.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class ItemServiceImpl implements ItemService {

	private final ItemRepository itemRepository;
	private final UserService userService;
	private final ModelMapper modelMapper;

	@Override
	public void saveItem(ItemRequest itemRequest) {
		Item item = modelMapper.map(itemRequest, Item.class);
		item.setUser(userService.getUser(itemRequest.getUid()));
		itemRepository.save(item);
	}

	@Override
	public List<ItemResponse> myItems(long uid) {
		List<Item> items = itemRepository.findAllByUser_Uid(uid);
		return items.stream()
			.map(item -> {
				ItemResponse map = modelMapper.map(item, ItemResponse.class);
				map.setUid(uid);
				return map;
			})
			.collect(Collectors.toList());
	}
}
