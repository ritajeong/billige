package com.ssafy.billige.contract.service.impl;

import com.ssafy.billige.contract.domain.Contract;
import com.ssafy.billige.contract.dto.request.ContractRequest;
import com.ssafy.billige.contract.repository.ContractRepository;
import com.ssafy.billige.contract.service.ContractService;
import com.ssafy.billige.item.domain.ActiveStatus;
import com.ssafy.billige.item.domain.Item;
import com.ssafy.billige.item.repository.ItemRepository;
import com.ssafy.billige.user.repository.UserRepository;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContractServiceImpl implements ContractService {

	private final ContractRepository contractRepository;
	private final UserRepository userRepository;
	private final ItemRepository itemRepository;
	private final ModelMapper modelMapper;

	@Override
	@Transactional
	public void contractSave(ContractRequest contractRequest) {
		Contract contract = modelMapper.map(contractRequest, Contract.class);
		contract.setUser(userRepository.findById(contractRequest.getUid())
			.orElseThrow(() -> new IllegalArgumentException("대여하는 회원의 정보를 확인해주세요.")));
		Item item = itemRepository.findById(contractRequest.getItemId())
			.orElseThrow(() -> new IllegalArgumentException("대여하려는 제품을 확인해주세요."));
		if (item.getIsActive().equals(ActiveStatus.Y)) {
			throw new IllegalArgumentException("대여가 비활성화된 제품입니다.");
		}
		contract.setItem(item);
		contractRepository.save(contract);
	}
}
