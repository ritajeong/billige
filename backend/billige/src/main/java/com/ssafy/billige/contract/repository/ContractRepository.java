package com.ssafy.billige.contract.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.billige.contract.domain.Contract;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long>, ContractRepositoryCustom {
	 List<Contract> findAllByUser_Uid(Long uid);
	 List<Contract> findAllByOwnerIdAndItem_ItemId(Long ownerId, Long itemId);
	List<Contract> findAllByItem_ItemId(Long itemId);
}
