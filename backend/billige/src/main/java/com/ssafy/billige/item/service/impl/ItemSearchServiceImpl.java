package com.ssafy.billige.item.service.impl;

import static com.ssafy.billige.utils.StringUtils.*;

import java.util.List;

import com.ssafy.billige.item.dto.response.ItemResponse;
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

	@Override
	public List<ItemResponse> getItems(int page, int userSigunguCode) {
		int offset = (page - 1) * LIMIT;
		return itemRepository.findAllItemResponseList(offset, userSigunguCode);
	}
}
