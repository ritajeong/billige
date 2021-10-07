package com.ssafy.billige.contract.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.billige.contract.domain.Contract;
import com.ssafy.billige.contract.dto.response.BorrowerResponse;
import com.ssafy.billige.contract.dto.response.ContractResponse;
import com.ssafy.billige.contract.repository.ContractRepository;
import com.ssafy.billige.contract.service.ContractSearchService;
import com.ssafy.billige.image.domain.Image;
import com.ssafy.billige.image.repository.ImageRepository;
import com.ssafy.billige.item.domain.Item;
import com.ssafy.billige.item.dto.response.ItemResponse;
import com.ssafy.billige.user.domain.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContractSearchServiceImpl implements ContractSearchService {

	private final ContractRepository contractRepository;
	private final ImageRepository imageRepository;

	@Override
	public ContractResponse contractInfo(Long contractId) {
		Contract contract = contractRepository.findById(contractId)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 대여품입니다."));
		Item item = contract.getItem();
		User user = contract.getUser();
		Image image = imageRepository.findAllByItem_ItemIdLimit1(item.getItemId());
		return ContractResponse.builder()
			.contractId(contractId)
			.itemname(item.getItemname())
			.position(item.getPosition())
			.borrowerId(user.getUid())
			.borrowerName(user.getUserName())
			.startDate(contract.getStartDate())
			.endDate(contract.getEndDate())
			.price(item.getPrice())
			.totalPrice(contract.getTotalPrice())
			.itemImage(image.getImgSrc())
			.build();
	}

	@Override
	public List<ItemResponse> myContracts(long uid) {
		List<ItemResponse> itemResponses = contractRepository.contractedItem(uid);
		for (ItemResponse response : itemResponses) {
			response.setImage(imageRepository.findAllByItem_ItemIdLimit1(response.getItemId()).getImgSrc());
		}
		return itemResponses;
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
