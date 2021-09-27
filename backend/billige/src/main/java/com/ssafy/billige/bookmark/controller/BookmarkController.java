package com.ssafy.billige.bookmark.controller;

import static com.ssafy.billige.utils.StringUtils.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.billige.bookmark.service.BookmarkService;
import com.ssafy.billige.utils.TokenUtils;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = BOOKMARK)
@RequiredArgsConstructor
public class BookmarkController {

	private final BookmarkService bookmarkService;

	@PostMapping("/{itemId}")
	public ResponseEntity<?> addBookmark(@PathVariable("itemId") Long itemId, @RequestHeader(AUTH_HEADER) String token) {
		Long uid = TokenUtils.getUidFromToken(token);
		bookmarkService.addBookmark(uid, itemId);
		return ResponseEntity.ok(SUCCESS);
	}

	@DeleteMapping("/{itemId}")
	public ResponseEntity<?> cancelBookmark(@PathVariable("itemId") Long itemId, @RequestHeader(AUTH_HEADER) String token) {
		Long uid = TokenUtils.getUidFromToken(token);
		bookmarkService.cancelBookmark(uid, itemId);
		return ResponseEntity.ok(SUCCESS);
	}
}
