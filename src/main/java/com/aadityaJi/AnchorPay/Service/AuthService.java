package com.aadityaJi.AnchorPay.Service;

import com.aadityaJi.AnchorPay.DTOs.LoginRequestDTO;
import com.aadityaJi.AnchorPay.DTOs.LoginResponseDTO;
import com.aadityaJi.AnchorPay.DTOs.RegisterRequestDTO;
import com.aadityaJi.AnchorPay.DTOs.RegisterResponseDTO;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    public RegisterResponseDTO registerUser(RegisterRequestDTO dto);
    public LoginResponseDTO login(LoginRequestDTO dto);
    public void logout(String token);
}
