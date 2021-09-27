package com.ssafy.billige.bookmark.service;

public interface BookmarkService {
	void addBookmark(Long uid, Long itemId);

	void cancelBookmark(Long uid, Long itemId);
}
