package com.ssafy.billige.item.service;

import java.util.List;

import com.ssafy.billige.item.domain.Item;

public interface ItemService {
	List<Item> myItems(long uid);
}
