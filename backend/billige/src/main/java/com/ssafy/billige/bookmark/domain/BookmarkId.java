package com.ssafy.billige.bookmark.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookmarkId implements Serializable {

    @Column(name = "uid")
    private Long uid;

    @Column(name = "item_id")
    private Long itemId;
}
