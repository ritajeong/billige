package com.ssafy.billige.item.controller;

import static com.ssafy.billige.utils.StringUtils.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.billige.item.service.ItemSearchService;
import com.ssafy.billige.utils.TokenUtils;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = ITEM)
@RequiredArgsConstructor
public class ItemSearchController {

	private final ItemSearchService itemSearchService;

	@GetMapping
	public ResponseEntity<?> myItems(@RequestHeader(AUTH_HEADER) String token) {
		Long uid = TokenUtils.getUidFromToken(token);
		return ResponseEntity.ok().body(itemSearchService.myItems(uid));
	}

	@GetMapping("/list")
	public ResponseEntity<?> getItems(HttpServletRequest request) {
		int userSigunguCode = 11110;
		try {
			String token = request.getHeader(AUTH_HEADER);
			userSigunguCode = TokenUtils.getSigunguCodeFromToken(token);
		} catch (Exception e) {	}
		return ResponseEntity.ok().body(itemSearchService.getItems(userSigunguCode));
	}

	@GetMapping("/detail/{itemId}")
	public ResponseEntity<?> getItemDetails(@PathVariable("itemId") Long itemId, HttpServletRequest request) {
		String token = "";
		Long uid = -1L;
		try {
			token = request.getHeader(AUTH_HEADER);
			uid = TokenUtils.getUidFromToken(token);
		} catch (NullPointerException e) {
		}
		return ResponseEntity.ok().body(itemSearchService.getItemDetails(itemId, uid));
	}
}
