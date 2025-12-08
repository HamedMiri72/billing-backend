package com.hamedTech.billing.mapper;

import com.hamedTech.billing.dto.item.ItemRequest;
import com.hamedTech.billing.dto.item.ItemResponse;
import com.hamedTech.billing.entity.Category;
import com.hamedTech.billing.entity.Item;

import java.util.UUID;

public class ItemMapper {

    public static Item toItem(ItemRequest itemRequest){
        return Item.builder()
                .itemId(UUID.randomUUID().toString())
                .name(itemRequest.getName())
                .price(itemRequest.getPrice())
                .description(itemRequest.getDescription())
                .build();
    }

    public static ItemResponse toItemResponse(Item item){
        return ItemResponse.builder()
                .name(item.getName())
                .itemId(item.getItemId())
                .categoryName(item.getCategory().getName())
                .categoryId(item.getCategory().getCategoryId())
                .description(item.getDescription())
                .price(item.getPrice())
                .imgUrl(item.getImgUrl())
                .createdAt(item.getCreatedAt())
                .updatedAt(item.getUpdatedAt())
                .build();
    }
}
