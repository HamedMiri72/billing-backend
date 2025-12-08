package com.hamedTech.billing.dto.item;

import com.hamedTech.billing.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemResponse {
    private String name;
    private String itemId;
    private String categoryName;
    private String categoryId;
    private String description;
    private BigDecimal price;
    private String imgUrl;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
