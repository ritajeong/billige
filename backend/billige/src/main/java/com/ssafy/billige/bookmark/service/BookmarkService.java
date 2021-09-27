package com.ssafy.billige.bookmark.service;

import java.util.List;

import com.ssafy.billige.bookmark.dto.response.BookmarkItemResponse;

public interface BookmarkService {
	void addBookmark(Long uid, Long itemId);

	void cancelBookmark(Long uid, Long itemId);

	List<BookmarkItemResponse> getBookmarkItems(Long uid);
}
