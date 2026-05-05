package com.aadityaJi.AnchorPay.Controller;

import com.aadityaJi.AnchorPay.DTOs.RegisterRequestDTO;
import com.aadityaJi.AnchorPay.DTOs.RegisterResponseDTO;
import com.aadityaJi.AnchorPay.Service.AuthService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> login(@Valid @RequestBody RegisterRequestDTO dto){
        try {
            RegisterResponseDTO responseBody = authService.registerUser(dto);
            return new ResponseEntity<>(responseBody,HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
