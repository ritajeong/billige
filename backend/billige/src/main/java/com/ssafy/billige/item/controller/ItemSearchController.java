package com.ssafy.billige.item.controller;

import static com.ssafy.billige.utils.StringUtils.*;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.billige.item.service.ItemSearchService;

import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;

@RestController
@RequestMapping(value = ITEM)
@RequiredArgsConstructor
public class ItemSearchController {

	private final ItemSearchService itemSearchService;

	@GetMapping("/{uid}")
	public ResponseEntity<?> myItems(@PathVariable("uid") Long uid) {
		return ResponseEntity.ok().body(itemSearchService.myItems(uid));
	}

	@GetMapping("/list")
	public ResponseEntity<?> getItems(@RequestParam("page") int page) {
		return ResponseEntity.ok().body(itemSearchService.getItems(page));
	}
}
