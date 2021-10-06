package com.ssafy.billige.contract.controller;

import static com.ssafy.billige.utils.StringUtils.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.billige.contract.service.ContractSearchService;
import com.ssafy.billige.utils.StringUtils;
import com.ssafy.billige.utils.TokenUtils;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = StringUtils.CONTRACT)
@RequiredArgsConstructor
public class ContractSearchController {

	private final ContractSearchService contractSearchService;

	@GetMapping("/rent")
	public ResponseEntity<?> rentItems(@RequestHeader(AUTH_HEADER) String token) {
		Long uid = TokenUtils.getUidFromToken(token);
		return ResponseEntity.ok().body(contractSearchService.myContracts(uid));
	}

	@GetMapping("/my/{itemId}")
	public ResponseEntity<?> myItems(@PathVariable("itemId") Long itemId, @RequestHeader(AUTH_HEADER) String token) {
		Long uid = TokenUtils.getUidFromToken(token);
		return ResponseEntity.ok().body(contractSearchService.myItemContracts(uid, itemId));
	}
}
