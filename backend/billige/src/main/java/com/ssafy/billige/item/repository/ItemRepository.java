package com.ssafy.billige.item.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.billige.item.domain.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
