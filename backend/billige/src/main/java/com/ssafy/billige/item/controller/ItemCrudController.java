package com.ssafy.billige.item.controller;

import static com.ssafy.billige.utils.StringUtils.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.billige.item.dto.request.ItemRegistryRequest;
import com.ssafy.billige.item.dto.request.ItemUpdateRequest;
import com.ssafy.billige.item.service.ItemCrudService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = ITEM)
@RequiredArgsConstructor
public class ItemCrudController {

	private final ItemCrudService itemCrudService;

	@PostMapping
	public ResponseEntity<?> createItem(@RequestBody ItemRegistryRequest itemRegistryRequest) {
		itemCrudService.saveItem(itemRegistryRequest);
		return ResponseEntity.ok().body(SUCCESS);
	}


	@PutMapping
	public ResponseEntity<?> itemUpdate(@RequestBody ItemUpdateRequest itemUpdateRequest) {
		itemCrudService.itemUpdate(itemUpdateRequest);
		return ResponseEntity.ok().body(SUCCESS);
	}

	@DeleteMapping("/{itemId}")
	public ResponseEntity<?> removeItem(@PathVariable("itemId") Long itemId) {
		itemCrudService.removeItem(itemId);
		return ResponseEntity.ok().body(SUCCESS);
	}

	@PutMapping("/active/{itemId}")
	public ResponseEntity<?> activeItem(@PathVariable("itemId") Long itemId) {
		itemCrudService.activeItem(itemId);
		return ResponseEntity.ok().body(SUCCESS);
	}
}
