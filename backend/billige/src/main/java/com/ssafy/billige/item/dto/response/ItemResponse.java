package com.ssafy.billige.item.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ItemResponse {
    private Long itemId;
    private String itemname;
    private String position;
    private int price;
    private LocalDateTime modifiedTime;
    private Long bookmarkCount;
}
