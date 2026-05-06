package com.aadityaJi.AnchorPay.Controller;

import com.aadityaJi.AnchorPay.DTOs.LoginRequestDTO;
import com.aadityaJi.AnchorPay.DTOs.LoginResponseDTO;
import com.aadityaJi.AnchorPay.DTOs.RegisterRequestDTO;
import com.aadityaJi.AnchorPay.DTOs.RegisterResponseDTO;
import com.aadityaJi.AnchorPay.Service.AuthService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/auth/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDTO dto){
        try {
            RegisterResponseDTO responseBody = authService.registerUser(dto);
            return new ResponseEntity<>(responseBody,HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO dto){
        try{
            LoginResponseDTO responseDTO = authService.login(dto);
            return new ResponseEntity<>(responseDTO,HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/user/logout")
    public ResponseEntity<String> logout(@RequestHeader(value = "Authorization",required = false) String authHeader){
        try {
            authService.logout(authHeader);
            return new ResponseEntity<>("Successfully logged-out",HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
