package com.ssafy.billige.bookmark.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter @Setter
@Embeddable
@NoArgsConstructor
public class BookmarkId {
    private Long uid;
    private Long item_id;
}
