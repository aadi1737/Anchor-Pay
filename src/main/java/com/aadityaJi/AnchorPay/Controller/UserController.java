package com.aadityaJi.AnchorPay.Controller;

import com.aadityaJi.AnchorPay.DTOs.AddMoneyRequestDTO;
import com.aadityaJi.AnchorPay.DTOs.SendMoneyRequestDTO;
import com.aadityaJi.AnchorPay.Response.ApiResponse;
import com.aadityaJi.AnchorPay.Service.TransactionService;
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
    private final TransactionService transactionService;

    public UserController(UserService userService, WalletService walletService, TransactionService transactionService) {
        this.userService = userService;
        this.walletService = walletService;
        this.transactionService = transactionService;
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
    public ResponseEntity<?> sendMoney(@RequestBody SendMoneyRequestDTO dto){
        ApiResponse<?> apiResponse = transactionService.sendMoney(dto);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @GetMapping("/transactions")
    public ResponseEntity<?> getTransactionHistory(){
        ApiResponse<?> response = transactionService.getTransactionHistory();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }


    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(){
        ApiResponse<?> response = userService.getProfile();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
