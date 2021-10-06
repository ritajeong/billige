package com.ssafy.billige.item.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.billige.item.dto.response.ItemListResponse;
import com.ssafy.billige.item.dto.response.ItemResponse;
import com.ssafy.billige.search.dto.request.SearchFilter;

import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.ssafy.billige.image.domain.QImage.*;
import static com.ssafy.billige.item.domain.ActiveStatus.*;
import static com.ssafy.billige.item.domain.QItem.*;
import static com.ssafy.billige.bookmark.domain.QBookmark.*;
import static com.ssafy.billige.utils.StringUtils.*;

@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<ItemResponse> findAllItemResponseList(int userSigunguCode) {
		return queryFactory
			.select(Projections.constructor(ItemResponse.class,
				item.itemId,
				item.itemname,
				item.position,
				item.price,
				item.modifiedTime,
				image.imgSrc))
			.from(item).leftJoin(image).on(item.itemId.eq(image.item.itemId))
			.where(item.isActive.eq(Y)
				.and(item.itemSigunguCode.eq(userSigunguCode)))
			.groupBy(item.itemId)
			.orderBy(item.modifiedTime.desc())
			.fetch();
	}

	@Override
	public List<ItemListResponse> findByCategory(String category, int offset) {
		return queryFactory
			.select(Projections.constructor(ItemListResponse.class,
				item.itemId,
				item.itemname,
				item.position,
				item.price,
				image.imgSrc))
			.from(item).leftJoin(image).on(item.itemId.eq(image.item.itemId))
			.where(item.category.eq(category))
			.limit(LIMIT)
			.offset(offset)
			.distinct()
			.fetch();
	}

	@Override
	public List<ItemListResponse> findByKeyword(String keyword, int offset) {
		return queryFactory
			.select(Projections.constructor(ItemListResponse.class,
				item.itemId,
				item.itemname,
				item.position,
				item.price,
				image.imgSrc))
			.from(item).leftJoin(image).on(item.itemId.eq(image.item.itemId))
			.where(item.itemname.contains(keyword))
			.limit(LIMIT)
			.offset(offset)
			.distinct()
			.fetch();
	}

	@Override
	public List<ItemListResponse> findByFilter(SearchFilter searchFilter, int offset) {
		return queryFactory
			.select(Projections.constructor(ItemListResponse.class,
				item.itemId,
				item.itemname,
				item.position,
				item.price,
				image.imgSrc))
			.from(item).leftJoin(image).on(item.itemId.eq(image.item.itemId))
			.where(item.price.between(searchFilter.getMin(), searchFilter.getMax())
				.and(item.category.eq(searchFilter.getCategory())
				.and(item.itemname.contains(searchFilter.getKeyword()))))
			.orderBy(item.modifiedTime.desc())
			.limit(LIMIT)
			.offset(offset)
			.distinct()
			.fetch();
	}

	@Override
	public List<ItemListResponse> findBookmarkItem(Long uid) {
		return queryFactory
			.select(Projections.constructor(ItemListResponse.class,
				item.itemId,
				item.itemname,
				item.position,
				item.price,
				image.imgSrc))
			.from(item).leftJoin(image).on(item.itemId.eq(image.item.itemId))
			.where(item.itemId.in(
				queryFactory.select(bookmark.bookmarkId.itemId)
				.from(bookmark)
				.where(bookmark.bookmarkId.uid.eq(uid))
			))
			.groupBy(item.itemId)
			.fetch();
	}

}
