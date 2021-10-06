package com.ssafy.billige.item.repository;

import java.util.List;

import com.ssafy.billige.item.dto.response.ItemListResponse;
import com.ssafy.billige.item.dto.response.ItemResponse;
import com.ssafy.billige.search.dto.request.SearchFilter;

public interface ItemRepositoryCustom {
    // 대여품 목록 가져오기
    List<ItemResponse> findAllItemResponseList(int userSigunguCode);

    // 카테고리 검색
    List<ItemListResponse> findByCategory(String category, int offset);

    // 키워드 검색
    List<ItemListResponse> findByKeyword(String keyword, int offset);

    // 필터 검색
    List<ItemListResponse> findByFilter(SearchFilter searchFilter, int offset);

    // 북마크 아이템 가져오기
    List<ItemListResponse> findBookmarkItem(Long uid);
}
