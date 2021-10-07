package com.ssafy.billige.contract.controller;

import static com.ssafy.billige.utils.StringUtils.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.billige.contract.dto.request.ContractRequest;
import com.ssafy.billige.contract.dto.response.ContractUnavailableResponse;
import com.ssafy.billige.contract.service.ContractService;
import com.ssafy.billige.utils.TokenUtils;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = CONTRACT)
@RequiredArgsConstructor
public class ContractController {

	private final ContractService contractService;

	@PostMapping("/save")
	public ResponseEntity<?> contractSave(@RequestBody ContractRequest contractRequest, @RequestHeader(AUTH_HEADER) String token) {
		Long uid = TokenUtils.getUidFromToken(token);
		contractRequest.setUid(uid);
		contractService.contractSave(contractRequest);
		return ResponseEntity.ok().body("success");
	}

	@GetMapping("/check/{itemId}")
	@ApiOperation("대여기간체크")
	public ResponseEntity<?> contractCheck(@RequestHeader(AUTH_HEADER) String token, @PathVariable Long itemId){
		String tokenEmail = TokenUtils.getUserEmailFromToken(token);
		ContractUnavailableResponse result = contractService.getUnavailableDate(tokenEmail, itemId);
		return ResponseEntity.ok().body(result);
	}
}
