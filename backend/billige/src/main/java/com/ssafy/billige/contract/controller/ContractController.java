package com.ssafy.billige.contract.controller;

import static com.ssafy.billige.utils.StringUtils.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.billige.contract.dto.request.ContractRequest;
import com.ssafy.billige.contract.service.ContractService;
import com.ssafy.billige.utils.TokenUtils;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = CONTRACT)
@RequiredArgsConstructor
public class ContractController {

	private final ContractService contractService;

	@PostMapping
	public ResponseEntity<?> contractSave(@RequestBody ContractRequest contractRequest, @RequestHeader(AUTH_HEADER) String token) {
		Long uid = TokenUtils.getUidFromToken(token);
		contractRequest.setUid(uid);
		contractService.contractSave(contractRequest);
		return ResponseEntity.ok().body("success");
	}
}
