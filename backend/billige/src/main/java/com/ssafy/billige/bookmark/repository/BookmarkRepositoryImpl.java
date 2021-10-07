package com.ssafy.billige.bookmark.repository;

import static com.ssafy.billige.bookmark.domain.QBookmark.*;
import static com.ssafy.billige.image.domain.QImage.*;
import static com.ssafy.billige.item.domain.QItem.*;

import java.util.List;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.billige.item.dto.response.ItemListResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BookmarkRepositoryImpl implements BookmarkRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<ItemListResponse> getBookmarkItems(Long uid) {
		return jpaQueryFactory.selectDistinct(Projections.constructor(ItemListResponse.class,
			item.itemId,
			item.itemname,
			item.position,
			item.price,
			image.imgSrc))
			.from(item).leftJoin(image).on(item.itemId.eq(image.item.itemId))
			.where(item.itemId.in(
				jpaQueryFactory.select(bookmark.bookmarkId.itemId)
				.from(bookmark)
				.where(bookmark.bookmarkId.uid.eq(uid))
			))
			.fetch();
	}
}
