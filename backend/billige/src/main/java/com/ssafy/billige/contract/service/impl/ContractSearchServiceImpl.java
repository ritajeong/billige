package com.ssafy.billige.contract.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.billige.contract.domain.Contract;
import com.ssafy.billige.contract.dto.response.MyContractResponse;
import com.ssafy.billige.contract.dto.response.MyItemContractResponse;
import com.ssafy.billige.contract.repository.ContractRepository;
import com.ssafy.billige.contract.service.ContractSearchService;
import com.ssafy.billige.item.dto.response.ItemResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContractSearchServiceImpl implements ContractSearchService {

	private final ContractRepository contractRepository;
	private final ModelMapper modelMapper;

	@Override
	public List<ItemResponse> myContracts(long uid) {
		return contractRepository.contractedItem(uid);
		// List<Contract> rentItem = contractRepository.findAllByUser_Uid(uid);
		// return rentItem.stream()
		// 	.map(item -> modelMapper.map(item, MyContractResponse.class))
		// 	.collect(Collectors.toList());
	}

	@Override
	public List<MyItemContractResponse> myItemContracts(long ownerId, long itemId) {
		List<Contract> myItem = contractRepository.findAllByOwnerIdAndItem_ItemId(ownerId, itemId);
		return myItem.stream()
			.map(item -> {
					MyItemContractResponse map = modelMapper.map(item, MyItemContractResponse.class);
					map.setBorrower(item.getUser());
					return map;
				}
			).collect(Collectors.toList());
	}
}
