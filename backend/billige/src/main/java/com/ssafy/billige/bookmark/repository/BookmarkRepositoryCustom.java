package com.ssafy.billige.bookmark.repository;

import java.util.List;

import com.ssafy.billige.item.dto.response.ItemListResponse;

public interface BookmarkRepositoryCustom {

	List<ItemListResponse> getBookmarkItems(Long uid);
}
