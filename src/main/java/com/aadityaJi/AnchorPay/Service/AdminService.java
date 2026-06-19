package com.aadityaJi.AnchorPay.Service;

import com.aadityaJi.AnchorPay.Response.ApiResponse;

public interface AdminService {
    ApiResponse<?> getAllUsers();
    ApiResponse<?> freezeUser(Long userId);
    ApiResponse<?> unfreezeUser(Long userId);
}