package com.aadityaJi.AnchorPay.Controller;

import com.aadityaJi.AnchorPay.DTOs.AddMoneyRequestDTO;
import com.aadityaJi.AnchorPay.Response.ApiResponse;
import com.aadityaJi.AnchorPay.Service.UserService;
import com.aadityaJi.AnchorPay.Service.WalletService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final WalletService walletService;

    public UserController(UserService userService, WalletService walletService) {
        this.userService = userService;
        this.walletService = walletService;
    }

    @GetMapping
    public ResponseEntity<?> checkBalance(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        ApiResponse<BigDecimal> response = walletService.getBalance(email);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/addMoney")
    private ResponseEntity<?> addMoney(@RequestBody AddMoneyRequestDTO dto){
        ApiResponse<?> apiResponse = walletService.addMoney(dto.getAmount());
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @PostMapping("/sendMoney")
    public ResponseEntity<?> sendMoney(@RequestBody )
}
