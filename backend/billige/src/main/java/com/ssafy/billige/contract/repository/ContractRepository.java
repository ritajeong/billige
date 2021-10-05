package com.ssafy.billige.contract.repository;

import java.util.List;

import com.ssafy.billige.contract.domain.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
	 List<Contract> findAllByUser_Uid(Long uid);
	 List<Contract> findAllByOwnerIdAndItem_ItemId(Long ownerId, Long itemId);
	List<Contract> findAllByItem_ItemId(Long itemId);
}
