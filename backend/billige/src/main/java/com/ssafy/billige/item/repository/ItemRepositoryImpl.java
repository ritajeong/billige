package com.ssafy.billige.item.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.billige.item.domain.ActiveStatus;
import com.ssafy.billige.item.dto.response.ItemResponse;

import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.ssafy.billige.item.domain.QItem.*;
import static com.ssafy.billige.bookmark.domain.QBookmark.*;
import static com.ssafy.billige.utils.StringUtils.*;

@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<ItemResponse> findAllItemResponseList(int offset, int userSigunguCode) {
		return queryFactory
			.select(Projections.constructor(ItemResponse.class,
				item.itemId,
				item.itemname,
				item.position,
				item.price,
				item.modifiedTime,
				bookmark.count().as("item_id")))
			.from(item)
			.leftJoin(bookmark).on(item.itemId.eq(bookmark.bookmarkId.itemId))
			.where(item.isActive.eq(ActiveStatus.Y)
				.and(item.itemSigunguCode.eq(userSigunguCode)))
			.groupBy(bookmark.bookmarkId.itemId)
			.orderBy(item.modifiedTime.desc())
			.limit(LIMIT)
			.offset(offset)
			.fetch();
	}
}
