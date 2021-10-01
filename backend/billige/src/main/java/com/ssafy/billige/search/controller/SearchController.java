package com.ssafy.billige.search.controller;

import static com.ssafy.billige.utils.StringUtils.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.billige.search.dto.request.SearchFilter;
import com.ssafy.billige.search.service.SearchService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = SEARCH)
@RequiredArgsConstructor
public class SearchController {

	private final SearchService searchService;

	@GetMapping("/category")
	public ResponseEntity<?> categorySearch(@RequestParam("page") int page, @RequestParam("category") String category) {
		int offset = (page - 1) * LIMIT;
		return ResponseEntity.ok().body(searchService.findByCategory(category, offset));
	}

	@GetMapping("/keyword")
	public ResponseEntity<?> keywordSearch(@RequestParam("page") int page, @RequestParam("keyword") String keyword) {
		int offset = (page - 1) * LIMIT;
		return ResponseEntity.ok().body(searchService.findByKeyword(keyword, offset));
	}

	@PostMapping
	public ResponseEntity<?> filterSearch(@RequestParam("page") int page, @RequestBody SearchFilter filter) {
		int offset = (page - 1) * LIMIT;
		return ResponseEntity.ok().body(searchService.findByFilter(filter, offset));
	}
}
