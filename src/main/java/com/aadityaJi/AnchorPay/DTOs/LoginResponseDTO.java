package com.aadityaJi.AnchorPay.DTOs;
import com.aadityaJi.AnchorPay.Entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginResponseDTO {

    private String jwtToken;
    private UserEntity.Role role;
    private Long walletID;
    private String username;

}
