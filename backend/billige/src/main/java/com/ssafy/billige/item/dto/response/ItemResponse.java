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

    public ItemResponse(Long itemId, String itemname, String position, int  price, LocalDateTime modifiedTime) {
        this.itemId = itemId;
        this.itemname = itemname;
        this.position = position;
        this.price = price;
        this.modifiedTime = modifiedTime;
    }
}
