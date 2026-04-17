package com.aadityaJi.AnchorPay.DTOs;

import com.aadityaJi.AnchorPay.Entity.TransactionEntity;
import com.aadityaJi.AnchorPay.Entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterResponseDTO {
    private String username;
}
