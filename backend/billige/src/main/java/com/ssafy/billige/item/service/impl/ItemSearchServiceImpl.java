package com.ssafy.billige.item.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.billige.item.domain.Item;
import com.ssafy.billige.item.repository.ItemRepository;
import com.ssafy.billige.item.service.ItemSearchService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemSearchServiceImpl implements ItemSearchService {

	private final ItemRepository itemRepository;

	@Override
	public List<Item> myItems(long uid) {
		return itemRepository.findAllByUser_UidOrderByModifiedTimeDesc(uid);
	}
}
