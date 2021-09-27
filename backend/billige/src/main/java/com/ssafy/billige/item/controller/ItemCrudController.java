package com.ssafy.billige.item.controller;

import static com.ssafy.billige.utils.StringUtils.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.billige.item.dto.request.ItemRegistryRequest;
import com.ssafy.billige.item.dto.request.ItemUpdateRequest;
import com.ssafy.billige.item.service.ItemCrudService;
import com.ssafy.billige.utils.TokenUtils;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = ITEM)
@RequiredArgsConstructor
public class ItemCrudController {

	private final ItemCrudService itemCrudService;

	@PostMapping
	public ResponseEntity<?> createItem(@RequestBody ItemRegistryRequest itemRegistryRequest, @RequestHeader(AUTH_HEADER) String token) {
		itemRegistryRequest.setUid(TokenUtils.getUidFromToken(token));
		itemCrudService.saveItem(itemRegistryRequest);
		return ResponseEntity.ok().body(SUCCESS);
	}


	@PutMapping
	public ResponseEntity<?> itemUpdate(@RequestBody ItemUpdateRequest itemUpdateRequest, @RequestHeader(AUTH_HEADER) String token) {
		Long uid = TokenUtils.getUidFromToken(token);
		itemCrudService.itemUpdate(itemUpdateRequest, uid);
		return ResponseEntity.ok().body(SUCCESS);
	}

	@DeleteMapping("/{itemId}")
	public ResponseEntity<?> removeItem(@PathVariable("itemId") Long itemId, @RequestHeader(AUTH_HEADER) String token) {
		Long uid = TokenUtils.getUidFromToken(token);
		itemCrudService.removeItem(itemId, uid);
		return ResponseEntity.ok().body(SUCCESS);
	}

	@PutMapping("/active/{itemId}")
	public ResponseEntity<?> activeItem(@PathVariable("itemId") Long itemId, @RequestHeader(AUTH_HEADER) String token) {
		Long uid = TokenUtils.getUidFromToken(token);
		itemCrudService.activeItem(itemId, uid);
		return ResponseEntity.ok().body(SUCCESS);
	}
}
