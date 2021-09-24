package com.ssafy.billige.contract.service;

import java.util.List;

import com.ssafy.billige.contract.dto.response.ContractSearchMyItemResponse;
import com.ssafy.billige.contract.dto.response.ContractSearchRendItemResponse;

public interface ContractSearchService {
	// 대여한 대여품 조회
	List<ContractSearchRendItemResponse> rentItemSearch(long uid);

	// 등록한 대여품 조회
	List<ContractSearchMyItemResponse> myItemSearch(long ownerId);
}
