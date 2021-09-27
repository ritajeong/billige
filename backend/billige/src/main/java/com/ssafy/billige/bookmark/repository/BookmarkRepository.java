package com.ssafy.billige.bookmark.repository;

import com.ssafy.billige.bookmark.domain.BookmarkId;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.billige.bookmark.domain.Bookmark;

public interface BookmarkRepository extends JpaRepository<Bookmark, BookmarkId> {
}
