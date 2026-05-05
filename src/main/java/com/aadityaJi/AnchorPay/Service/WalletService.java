package com.aadityaJi.AnchorPay.Service;

import com.aadityaJi.AnchorPay.DTOs.WalletRequestDTO;
import com.aadityaJi.AnchorPay.DTOs.WalletResponseDTO;
import com.aadityaJi.AnchorPay.Entity.WalletEntity;

public interface WalletService {
    public WalletEntity addWallet(WalletRequestDTO dto);
}
