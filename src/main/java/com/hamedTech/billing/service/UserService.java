package com.hamedTech.billing.service;


import com.hamedTech.billing.dto.UserRequest;
import com.hamedTech.billing.dto.UserResponse;

import java.util.List;

public interface UserService {


    UserResponse createUser(UserRequest userRequest);

    String getUserRole(String email);

    List<UserResponse> getAllUsers();

    void deleteUser(String userId);
}
