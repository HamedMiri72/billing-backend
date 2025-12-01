package com.hamedTech.billing.dto;


import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest {


    private String name;
    private String description;
    private String bgColor;


}
