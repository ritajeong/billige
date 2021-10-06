package com.ssafy.billige.item.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.billige.bookmark.domain.BookmarkId;
import com.ssafy.billige.bookmark.repository.BookmarkRepository;
import com.ssafy.billige.image.domain.Image;
import com.ssafy.billige.image.repository.ImageRepository;
import com.ssafy.billige.item.domain.Item;
import com.ssafy.billige.item.dto.response.ItemDetailResponse;
import com.ssafy.billige.item.dto.response.ItemResponse;
import com.ssafy.billige.item.repository.ItemRepository;
import com.ssafy.billige.item.service.ItemSearchService;
import com.ssafy.billige.user.domain.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemSearchServiceImpl implements ItemSearchService {

	private final ItemRepository itemRepository;
	private final ImageRepository imageRepository;
	private final BookmarkRepository bookmarkRepository;

	@Override
	public List<Item> myItems(long uid) {
		return itemRepository.findAllByUser_UidOrderByModifiedTimeDesc(uid);
	}

	@Override
	public List<ItemResponse> getItems(int userSigunguCode) {
		List<ItemResponse> response = itemRepository.findAllItemResponseList(userSigunguCode);
		for (ItemResponse itemResponse : response) {
			itemResponse.setBookmarkCount(bookmarkRepository.countByItemId(itemResponse.getItemId()));
		}
		return response;
	}

	@Override
	public ItemDetailResponse getItemDetails(long itemId, long uid) {
		Item item = itemRepository.findById(itemId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 제품입니다."));
		List<Image> images = imageRepository.findAllByItem_ItemId(itemId);
		User owner = item.getUser();
		ItemDetailResponse response = ItemDetailResponse.builder()
			.itemId(itemId)
			.itemname(item.getItemname())
			.category(item.getCategory())
			.description(item.getDescription())
			.position(item.getPosition())
			.price(item.getPrice())
			.modifiedTime(item.getModifiedTime())
			.build();

		boolean isBookmark = uid != -1 && bookmarkRepository.findById(new BookmarkId(uid, itemId)).isPresent();
		response.setBookmark(isBookmark);

		response.setOwner(
			ItemDetailResponse.Owner.builder()
				.uid(owner.getUid())
				.username(owner.getUserName())
				.wallet(owner.getUserWallet())
				.address(owner.getUserAddress())
				.image(owner.getUserImage())
				.build()
		);
		response.setImgSrc(
			images.stream()
				.map(Image::getImgSrc)
				.collect(Collectors.toList())
		);
		return response;
	}
}
