package com.hamedTech.billing.mapper;

import com.hamedTech.billing.dto.UserRequest;
import com.hamedTech.billing.dto.UserResponse;
import com.hamedTech.billing.entity.UserEntity;

import java.util.UUID;

public class UserMapper {


    public static UserEntity toUserEntity(UserRequest userRequest){
        return UserEntity.builder()
                .userId(UUID.randomUUID().toString())
                .email(userRequest.getEmail())
                .name(userRequest.getName())
                .role(userRequest.getRole().toUpperCase())
                .build();
    }

    public static UserResponse toUserResponse(UserEntity userEntity){
        return UserResponse.builder()
                .userId(userEntity.getUserId())
                .email(userEntity.getEmail())
                .name(userEntity.getName())
                .role(userEntity.getRole())
                .createdAt(userEntity.getCreatedAt())
                .updatedAt(userEntity.getUpdatedAt())
                .build();
    }

}
