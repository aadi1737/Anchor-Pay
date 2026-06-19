package com.aadityaJi.AnchorPay.Service.Impl;

import com.aadityaJi.AnchorPay.Entity.UserEntity;
import com.aadityaJi.AnchorPay.Repository.UserRepository;
import com.aadityaJi.AnchorPay.Response.ApiResponse;
import com.aadityaJi.AnchorPay.Service.UserService;
import com.aadityaJi.AnchorPay.Service.WalletService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final WalletService walletService;

    public UserServiceImpl(UserRepository userRepository, WalletService walletService) {
        this.userRepository = userRepository;
        this.walletService = walletService;
    }

    public ApiResponse<?> getProfile() {
        UserEntity user = walletService.findUserByFromSCH();
        return ApiResponse.builder()
                .success(true)
                .message("Profile fetched")
                .data(user)
                .build();
    }
}
