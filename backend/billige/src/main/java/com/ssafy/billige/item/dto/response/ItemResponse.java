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
    private String image;


    public ItemResponse(Long itemId, String itemname, String position, int  price, LocalDateTime modifiedTime, String image) {
        this.itemId = itemId;
        this.itemname = itemname;
        this.position = position;
        this.price = price;
        this.modifiedTime = modifiedTime;
        this.image = image;
    }
}
