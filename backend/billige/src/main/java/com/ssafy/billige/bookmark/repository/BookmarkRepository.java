package com.ssafy.billige.bookmark.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.billige.bookmark.domain.Bookmark;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
}
