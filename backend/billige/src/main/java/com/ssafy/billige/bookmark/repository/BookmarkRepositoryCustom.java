package com.ssafy.billige.bookmark.repository;

import java.util.List;

import com.ssafy.billige.bookmark.dto.response.BookmarkItemResponse;

public interface BookmarkRepositoryCustom {

	List<BookmarkItemResponse> getBookmarkItems(Long uid);
}
