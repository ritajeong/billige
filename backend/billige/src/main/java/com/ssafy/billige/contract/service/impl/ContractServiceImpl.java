package com.ssafy.billige.contract.service.impl;

import com.ssafy.billige.contract.domain.Contract;
import com.ssafy.billige.contract.dto.request.ContractRequest;
import com.ssafy.billige.contract.dto.response.ContractUnavailableResponse;
import com.ssafy.billige.contract.repository.ContractRepository;
import com.ssafy.billige.contract.service.ContractService;
import com.ssafy.billige.item.domain.ActiveStatus;
import com.ssafy.billige.item.domain.Item;
import com.ssafy.billige.item.repository.ItemRepository;
import com.ssafy.billige.user.domain.User;
import com.ssafy.billige.user.repository.UserRepository;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
		// Contract contract = modelMapper.map(contractRequest, Contract.class);
		User user = userRepository.findById(contractRequest.getUid())
			.orElseThrow(() -> new IllegalArgumentException("대여하는 회원의 정보를 확인해주세요."));
		Item item = itemRepository.findById(contractRequest.getItemId())
			.orElseThrow(() -> new IllegalArgumentException("대여하려는 제품을 확인해주세요."));
		if (item.getIsActive().equals(ActiveStatus.N)) {
			throw new IllegalArgumentException("대여가 비활성화된 제품입니다.");
		}
		Contract contract = Contract.builder()
			.item(item)
			.ownerId(contractRequest.getOwnerId())
			.startDate(contractRequest.getStartDate())
			.endDate(contractRequest.getStartDate())
			.user(user)
			.totalPrice(contractRequest.getTotalPrice())
			.build();
		// 금액 확인
		// int holdingBalance = contract.getUser().getUserBli();
		// if(holdingBalance < contract.getTotalPrice()){
		// 	throw new IllegalArgumentException("잔액이 부족합니다.");
		// }
		// 동시 대여처리

		// 금액에 대해서 대여하는사람의 잔액처리
		// contract.getUser().setUserBli(contract.getUser().getUserBli() - contract.getTotalPrice());
		// 금액에 대해서 대여해주는 사람의 잔액 처리
		// int amount = userRepository.findById(contract.getOwnerId()).get().getUserBli();
		// amount += contract.getTotalPrice();
		// userRepository.findById(contract.getOwnerId()).get().setUserBli(amount);
		// userRepo.save 해야되는지 확인할 것
		// 저장 전 기간 확인
		if(!lastCheck(contractRequest)){
			// 동시저장발생 -> 거래 취소해야함
			throw new IllegalArgumentException("이미 마감된 거래입니다.");
		}
		contract.setItem(item);
		contractRepository.save(contract);
		// 일단 대여하기 눌렀을때 그 물품이 대여가능한지 기간조회해서 결과 뿌려주는거 따로만들기
	}
	@Override
	@Transactional
	public boolean lastCheck(ContractRequest contractRequest){
		Long itemId = contractRequest.getItemId();
		Long ownerId = contractRequest.getOwnerId();
		LocalDate startDate = contractRequest.getStartDate();
		LocalDate endDate = contractRequest.getEndDate();
		List<Contract> list = contractRepository.findAllByOwnerIdAndItem_ItemId(ownerId, itemId);
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getEndDate().isBefore(startDate) || list.get(i).getStartDate().isAfter(endDate)) continue;
			else return false;
		}
		return true;
	}

	@Override
	@Transactional
	public ContractUnavailableResponse getUnavailableDate(String tokenEmail, Long itemId){
		Long uid = userRepository.findByUserEmail(tokenEmail).get().getUid();

		List<Item> itemList = itemRepository.findByUser_Uid(uid);
		for (int i = 0; i < itemList.size(); i++) {
			if(itemList.get(i).getItemId() == itemId){
				throw new IllegalArgumentException("본인의 물건은 대여할 수 없습니다.");
			}
		}

		int currentMonth = LocalDate.now().getMonthValue();
		int afterMonth = currentMonth + 1;
		ContractUnavailableResponse result = new ContractUnavailableResponse();
		List<LocalDate> dayList = new ArrayList<>();
		List<Contract> list = contractRepository.findAllByItem_ItemId(itemId);

		for (int i = 0; i < list.size(); i++) {
			int month = (int) list.get(i).getEndDate().getMonthValue();
			int day = list.get(i).getEndDate().getDayOfMonth();
			if(list.get(i).getEndDate().isAfter(LocalDate.now()) && month <= afterMonth){ // 해당 아이템의 거래확정 내역 중 오늘 이후에 끝나면서 종료월이 다음날 이내라면
				// 그 시작부터 종료까지의 날짜를 담아라
				LocalDate startValue = list.get(i).getStartDate();
				LocalDate endValue = list.get(i).getEndDate();
				while(startValue.isBefore(endValue) || startValue.isEqual(endValue)){
					dayList.add(startValue);
					startValue = startValue.plusDays(1);
				}
			}
		}
		result.setUnavailableList(dayList);
		return result;
	}
}
