package com.ssafy.billige.item.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.billige.item.domain.Item;
import com.ssafy.billige.item.dto.request.ItemRegistryRequest;
import com.ssafy.billige.item.dto.request.ItemUpdateRequest;
import com.ssafy.billige.item.repository.ItemRepository;
import com.ssafy.billige.item.service.ItemService;
import com.ssafy.billige.user.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemServiceImpl implements ItemService {

	private final ItemRepository itemRepository;

	@Override
	public List<Item> myItems(long uid) {
		return itemRepository.findAllByUser_Uid(uid);
	}
}
