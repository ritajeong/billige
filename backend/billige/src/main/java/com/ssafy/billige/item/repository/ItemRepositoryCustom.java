package com.ssafy.billige.item.repository;

import com.ssafy.billige.item.dto.response.ItemResponse;

import java.util.List;

public interface ItemRepositoryCustom {
    // 대여품 목록 가져오기
    List<ItemResponse> findAllItemResponseList(int page);
}
