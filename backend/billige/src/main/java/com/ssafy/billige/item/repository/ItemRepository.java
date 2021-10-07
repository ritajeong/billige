package com.ssafy.billige.item.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.billige.item.domain.Item;

public interface ItemRepository extends JpaRepository<Item, Long>, ItemRepositoryCustom {
	List<Item> findByUser_Uid(Long uid);
}
