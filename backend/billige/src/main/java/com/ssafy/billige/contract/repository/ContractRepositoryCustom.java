package com.ssafy.billige.contract.repository;

import java.util.List;

import com.ssafy.billige.item.dto.response.ItemResponse;

public interface ContractRepositoryCustom {
	List<ItemResponse> contractedItem(Long uid);
}
