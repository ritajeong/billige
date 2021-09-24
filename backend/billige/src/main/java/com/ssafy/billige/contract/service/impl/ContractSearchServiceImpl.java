package com.ssafy.billige.contract.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.billige.contract.domain.Contract;
import com.ssafy.billige.contract.dto.response.ContractSearchMyItemResponse;
import com.ssafy.billige.contract.dto.response.ContractSearchRendItemResponse;
import com.ssafy.billige.contract.repository.ContractRepository;
import com.ssafy.billige.contract.service.ContractSearchService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContractSearchServiceImpl implements ContractSearchService {

	private final ContractRepository contractRepository;
	private final ModelMapper modelMapper;

	@Override
	public List<ContractSearchRendItemResponse> rentItemSearch(long uid) {
		List<Contract> rentItem = contractRepository.findAllByUser_Uid(uid);
		return rentItem.stream()
			.map(item -> modelMapper.map(item, ContractSearchRendItemResponse.class))
			.collect(Collectors.toList());
	}

	@Override
	public List<ContractSearchMyItemResponse> myItemSearch(long ownerId) {
		List<Contract> myItem = contractRepository.findAllByOwnerId(ownerId);
		return myItem.stream()
			.map(item -> {
					ContractSearchMyItemResponse map = modelMapper.map(item, ContractSearchMyItemResponse.class);
					map.setBorrower(item.getUser());
					return map;
				}
			).collect(Collectors.toList());
	}
}
