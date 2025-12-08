package com.hamedTech.billing.dto;

import com.hamedTech.billing.dto.item.ItemResponse;
import com.hamedTech.billing.entity.Item;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {


    private String categoryId;
    private String name;
    private String description;
    private String bgColor;
    private String imgUrl;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private List<ItemResponse> items;
}
