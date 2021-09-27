package com.ssafy.billige.bookmark.repository;

import java.util.List;

import com.ssafy.billige.bookmark.domain.BookmarkId;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.billige.bookmark.domain.Bookmark;

public interface BookmarkRepository extends JpaRepository<Bookmark, BookmarkId>, BookmarkRepositoryCustom{

	List<Bookmark> findAllByBookmarkId_Uid(Long uid);
}
