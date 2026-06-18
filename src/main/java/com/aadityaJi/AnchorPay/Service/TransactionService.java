package com.aadityaJi.AnchorPay.Service;

import com.aadityaJi.AnchorPay.DTOs.SendMoneyRequestDTO;
import com.aadityaJi.AnchorPay.Response.ApiResponse;

public interface TransactionService {
    ApiResponse<?> sendMoney(SendMoneyRequestDTO dto);
}
