package com.ssafy.billige.contract.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.billige.contract.domain.Contract;
import com.ssafy.billige.contract.dto.response.BorrowerResponse;
import com.ssafy.billige.contract.repository.ContractRepository;
import com.ssafy.billige.contract.service.ContractSearchService;
import com.ssafy.billige.item.domain.Item;
import com.ssafy.billige.item.dto.response.ItemResponse;
import com.ssafy.billige.user.domain.User;

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
	}

	@Override
	public List<BorrowerResponse> myItemContracts(long ownerId, long itemId) {
		List<Contract> myItem = contractRepository.findAllByOwnerIdAndItem_ItemId(ownerId, itemId);
		List<BorrowerResponse> borrowers = new ArrayList<>();
		for (Contract contract : myItem) {
			User user = contract.getUser();
			Item item = contract.getItem();
			BorrowerResponse borrower = BorrowerResponse.builder()
				.contract_id(contract.getContractId())
				.borrower_id(user.getUid())
				.username(user.getUserName())
				.userImage(user.getUserImage())
				.itemId(itemId)
				.position(item.getPosition())
				.startDate(contract.getStartDate())
				.endDate(contract.getEndDate())
				.build();
			borrowers.add(borrower);
		}
		return borrowers;
	}
}
