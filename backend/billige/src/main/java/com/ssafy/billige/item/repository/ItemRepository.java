package com.ssafy.billige.item.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.billige.item.domain.Item;

public interface ItemRepository extends JpaRepository<Item, Long>, ItemRepositoryCustom {
	List<Item> findAllByUser_UidOrderByModifiedTimeDesc(Long uid);

	Long findItemIdByUser_Uid(Long uid);
}
