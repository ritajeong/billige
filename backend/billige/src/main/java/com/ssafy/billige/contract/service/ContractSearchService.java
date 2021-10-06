package com.ssafy.billige.contract.service;

import java.util.List;

import com.ssafy.billige.contract.dto.response.MyContractResponse;
import com.ssafy.billige.contract.dto.response.MyItemContractResponse;

public interface ContractSearchService {
	// 대여한 대여품 조회
	List<MyContractResponse> myContracts(long uid);

	// 등록한 대여품 조회
	List<MyItemContractResponse> myItemContracts(long ownerId, long itemId);
}
