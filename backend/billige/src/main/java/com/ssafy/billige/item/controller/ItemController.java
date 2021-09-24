package com.ssafy.billige.item.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.billige.item.dto.request.ItemRequest;
import com.ssafy.billige.item.service.ItemService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/item")
@RequiredArgsConstructor
public class ItemController {

	private final ItemService itemService;

	@PostMapping
	public ResponseEntity<?> createItem(@RequestBody ItemRequest itemRequest) {
		itemService.saveItem(itemRequest);
		return ResponseEntity.ok().body("success");
	}
}
