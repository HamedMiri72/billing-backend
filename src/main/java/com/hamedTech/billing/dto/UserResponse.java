package com.hamedTech.billing.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    private String userId;
    private String email;
    private String name;
    private String role;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
