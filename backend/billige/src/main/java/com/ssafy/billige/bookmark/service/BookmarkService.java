package com.ssafy.billige.bookmark.service;

import java.util.List;

import com.ssafy.billige.item.dto.response.ItemListResponse;

public interface BookmarkService {
	void addBookmark(Long uid, Long itemId);

	void cancelBookmark(Long uid, Long itemId);

	List<ItemListResponse> getBookmarkItems(Long uid);
}
