package com.aadityaJi.AnchorPay.Service;

import com.aadityaJi.AnchorPay.DTOs.WalletRequestDTO;
import com.aadityaJi.AnchorPay.DTOs.WalletResponseDTO;
import com.aadityaJi.AnchorPay.Entity.WalletEntity;
import com.aadityaJi.AnchorPay.Response.ApiResponse;

import java.math.BigDecimal;

public interface WalletService {
    public WalletEntity addWallet(WalletRequestDTO dto);
    public ApiResponse<BigDecimal> getBalance(String email);
    public ApiResponse<?> addMoney(BigDecimal amount);
}
