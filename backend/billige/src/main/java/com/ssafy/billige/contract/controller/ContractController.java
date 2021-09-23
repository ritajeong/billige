package com.ssafy.billige.contract.controller;

import static com.ssafy.billige.utils.StringUtils.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.billige.contract.dto.ContractRequest;
import com.ssafy.billige.contract.service.ContractService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = CONTRACT)
@RequiredArgsConstructor
public class ContractController {

	private final ContractService contractService;

	@PostMapping
	public ResponseEntity<?> contractSave(@RequestBody ContractRequest contractRequest) {
		contractService.contractSave(contractRequest);
		return ResponseEntity.ok().body("success");
	}
}
