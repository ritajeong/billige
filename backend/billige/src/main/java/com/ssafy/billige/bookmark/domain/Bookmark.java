package com.ssafy.billige.bookmark.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bookmark {
	@EmbeddedId
	private BookmarkId bookmarkId;
}
