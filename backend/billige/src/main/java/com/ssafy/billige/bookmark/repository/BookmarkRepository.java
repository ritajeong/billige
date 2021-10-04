package com.ssafy.billige.bookmark.repository;

import java.util.List;

import com.ssafy.billige.bookmark.domain.BookmarkId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssafy.billige.bookmark.domain.Bookmark;

public interface BookmarkRepository extends JpaRepository<Bookmark, BookmarkId>, BookmarkRepositoryCustom{

	List<Bookmark> findAllByBookmarkId_Uid(Long uid);

	@Query(value = "select count(*) from bookmark where item_id = :itemId", nativeQuery = true)
	Long countByItemId(@Param("itemId") Long itemId);
}
