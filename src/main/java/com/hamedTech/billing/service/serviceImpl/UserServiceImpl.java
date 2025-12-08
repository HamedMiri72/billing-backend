package com.hamedTech.billing.service.serviceImpl;

import com.hamedTech.billing.dto.UserRequest;
import com.hamedTech.billing.dto.UserResponse;
import com.hamedTech.billing.entity.UserEntity;
import com.hamedTech.billing.exception.ResourceNotFoundException;
import com.hamedTech.billing.mapper.UserMapper;
import com.hamedTech.billing.repository.UserRepository;
import com.hamedTech.billing.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse createUser(UserRequest userRequest) {

        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }

        UserEntity newUser = UserMapper.toUserEntity(userRequest);
        String encodedPassword = passwordEncoder.encode(userRequest.getPassword());
        newUser.setPassword(encodedPassword);

        userRepository.save(newUser);

        UserResponse response = UserMapper.toUserResponse(newUser);

        return response;
    }

    @Override
    public String getUserRole(String email) {
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User with provided email is not found" + email));

        String role = userEntity.getRole();

        return role;
    }

    @Override
    public List<UserResponse> getAllUsers() {

        List<UserEntity> users = userRepository.findAll();
        List<UserResponse> response = users.stream()
                .map(user -> UserMapper.toUserResponse(user))
                .toList();

        return response;
    }

    @Override
    public void deleteUser(String userId) {

        UserEntity userEntity = userRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User can not be found with this user id::" + userId));


        userRepository.delete(userEntity);

    }
}
