package com.aadityaJi.AnchorPay.Service.Impl;

import com.aadityaJi.AnchorPay.Entity.UserEntity;
import com.aadityaJi.AnchorPay.Repository.UserRepository;
import com.aadityaJi.AnchorPay.Response.ApiResponse;
import com.aadityaJi.AnchorPay.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ApiResponse<?> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        return ApiResponse.builder()
                .success(true)
                .message("All users fetched")
                .data(users)
                .build();
    }

    @Override
    public ApiResponse<?> freezeUser(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setActive(false);
        userRepository.save(user);
        return ApiResponse.builder()
                .success(true)
                .message("User frozen successfully")
                .build();
    }

    @Override
    public ApiResponse<?> unfreezeUser(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setActive(true);
        userRepository.save(user);
        return ApiResponse.builder()
                .success(true)
                .message("User unfrozen successfully")
                .build();
    }
}