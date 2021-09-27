package com.ssafy.billige.bookmark.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssafy.billige.bookmark.dto.response.BookmarkItemResponse;

@SpringBootTest
class BookmarkRepositoryImplTest {

	@Autowired
	private BookmarkRepository repository;

	@Test
	public void Test() {
		List<BookmarkItemResponse> items = repository.getBookmarkItems(1L);
		assertEquals(items.size(), 2);

		items = repository.getBookmarkItems(6L);
		assertEquals(items.size(), 1);
	}
}