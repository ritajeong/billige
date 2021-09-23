package com.ssafy.billige.contract.repository;

import java.util.List;

import com.ssafy.billige.contract.domain.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
	 List<Contract> findAllByUser_Uid(Long uid);
	 List<Contract> findAllByOwnerId(Long ownerId);
}
