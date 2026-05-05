package com.aadityaJi.AnchorPay.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class WalletRequestDTO {

    @NotBlank(message = "Wallet owner id is mandatory.")
    private long uid;
}
