package com.ssafy.billige.item.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.billige.item.dto.response.ItemResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.ssafy.billige.item.domain.QItem.*;
import static com.ssafy.billige.bookmark.domain.QBookmark.*;

@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ItemResponse> findAllItemResponseList() {
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
                .groupBy(bookmark.bookmarkId.itemId)
                .orderBy(item.modifiedTime.desc())
                .fetch();
    }
}
