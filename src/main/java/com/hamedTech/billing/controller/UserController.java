package com.hamedTech.billing.controller;

import com.hamedTech.billing.dto.UserRequest;
import com.hamedTech.billing.dto.UserResponse;
import com.hamedTech.billing.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest userRequest){

        UserResponse userResponse = userService.createUser(userRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }


    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAllUsers(){

        List<UserResponse> users = userService.getAllUsers();

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/role/{email}")
    public ResponseEntity<String> getRole(@PathVariable String email){
        String role = userService.getUserRole(email);

        return ResponseEntity.status(HttpStatus.OK).body(role);
    }

    @DeleteMapping("/users/delete/{userId}")
    public void deleteUser(@PathVariable String userId){
        userService.deleteUser(userId);
    }
}
