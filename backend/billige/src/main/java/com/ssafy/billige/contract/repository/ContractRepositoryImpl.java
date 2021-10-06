package com.ssafy.billige.contract.repository;

import static com.ssafy.billige.image.domain.QImage.*;
import static com.ssafy.billige.contract.domain.QContract.*;
import static com.ssafy.billige.item.domain.QItem.*;

import java.util.List;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.billige.item.dto.response.ItemResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ContractRepositoryImpl implements ContractRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<ItemResponse> contractedItem(Long uid) {
		return queryFactory
			.select(Projections.constructor(ItemResponse.class,
				item.itemId,
				item.itemname,
				item.position,
				item.price,
				item.modifiedTime,
				image.imgSrc))
			.from(item).leftJoin(image).on(item.itemId.eq(image.item.itemId))
			.where(item.itemId.in(
				queryFactory.select(contract.item.itemId)
					.from(contract)
					.where(contract.user.uid.eq(uid))
			))
			.groupBy(item.itemId)
			.orderBy(item.modifiedTime.desc())
			.fetch();
	}
}
