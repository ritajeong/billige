package com.ssafy.billige.contract.service;

import java.util.List;

import com.ssafy.billige.contract.dto.response.BorrowerResponse;
import com.ssafy.billige.item.dto.response.ItemResponse;

public interface ContractSearchService {
	// 대여한 대여품 조회
	List<ItemResponse> myContracts(long uid);

	// 등록한 대여품 조회
	List<BorrowerResponse> myItemContracts(long ownerId, long itemId);
}
