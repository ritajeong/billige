package com.ssafy.billige.search.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.billige.item.dto.response.ItemListResponse;
import com.ssafy.billige.item.repository.ItemRepository;
import com.ssafy.billige.search.dto.request.SearchFilter;
import com.ssafy.billige.search.service.SearchService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

	private final ItemRepository itemRepository;

	@Override
	public List<ItemListResponse> findByCategory(String category, int offset) {
		return itemRepository.findByCategory(category, offset);
	}

	@Override
	public List<ItemListResponse> findByKeyword(String keyword, int offset) {
		return itemRepository.findByKeyword(keyword, offset);
	}

	@Override
	public List<ItemListResponse> findByFilter(SearchFilter searchFilter, int offset) {
		return itemRepository.findByFilter(searchFilter, offset);
	}
}
