package com.ssafy.billige.search.service;

import java.util.List;

import com.ssafy.billige.item.dto.response.ItemListResponse;
import com.ssafy.billige.search.dto.request.SearchFilter;

public interface SearchService {

	// 카테고리 검색
	List<ItemListResponse> findByCategory(String category, int offset);

	// 키워드 검색
	List<ItemListResponse> findByKeyword(String keyword, int offset);

	// 필터 검색
	List<ItemListResponse> findByFilter(SearchFilter searchFilter, int offset);
}
