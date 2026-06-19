package com.aadityaJi.AnchorPay.Service.Impl;

import com.aadityaJi.AnchorPay.Config.EmailService;
import com.aadityaJi.AnchorPay.DTOs.SendMoneyRequestDTO;
import com.aadityaJi.AnchorPay.Entity.TransactionEntity;
import com.aadityaJi.AnchorPay.Entity.UserEntity;
import com.aadityaJi.AnchorPay.Repository.TransactionRepository;
import com.aadityaJi.AnchorPay.Repository.UserRepository;
import com.aadityaJi.AnchorPay.Repository.WalletRepository;
import com.aadityaJi.AnchorPay.Response.ApiResponse;
import com.aadityaJi.AnchorPay.Service.TransactionService;
import com.aadityaJi.AnchorPay.Service.WalletService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionalServiceImpl implements TransactionService {


    private final WalletService walletService;
    private final UserRepository userRepository;
    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;
    private final EmailService emailService;

    public TransactionalServiceImpl(WalletService walletService, UserRepository userRepository, UserRepository userRepository1, WalletRepository walletRepository, TransactionRepository transactionRepository, EmailService emailService) {
        this.walletService = walletService;
        this.userRepository = userRepository1;
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
        this.emailService = emailService;
    }

    @Override
    @Transactional
    public ApiResponse<?> sendMoney(SendMoneyRequestDTO dto) {
        UserEntity sender = walletService.findUserByFromSCH();

        UserEntity reciever = userRepository.findByEmail(dto.getReceiverMail()).orElseThrow(() -> new RuntimeException("Reciever Not Found"));

        if(sender.getWallet().getBalance().compareTo(dto.getAmount()) < 0){
            throw new RuntimeException("Insufficient Amount");
        }

        sender.getWallet().setBalance(sender.getWallet().getBalance().subtract(dto.getAmount()));

        reciever.getWallet().setBalance(
                reciever.getWallet().getBalance().add(dto.getAmount()));

        TransactionEntity transaction = TransactionEntity.builder()
                .senderId(sender.getId())
                .receiverId(reciever.getId())
                .amount(dto.getAmount())
                .status(TransactionEntity.Status.SUCCESS)
                .type(TransactionEntity.Type.TRANSFER)
                .description(dto.getDescription())
                .build();

        walletRepository.save(sender.getWallet());
        walletRepository.save(reciever.getWallet());
        transactionRepository.save(transaction);

        emailService.sendMail(
                sender.getEmail(), "AnchorPay: Money Sent", "Rs "+ dto.getAmount() +
                "sent to:" + reciever.getEmail() + " successfully.z" );

        return ApiResponse.builder()
                .success(true)
                .message("Money sent successfully")
                .data(dto.getAmount())
                .build();
    }

    @Override
    public ApiResponse<?> getTransactionHistory() {
        UserEntity user = walletService.findUserByFromSCH();

        List<TransactionEntity> transactions = transactionRepository.findBySenderIdOrReceiverId(user.getId(), user.getId());

        return ApiResponse.builder()
                .success(true)
                .message("Transaction history fetched")
                .data(transactions)
                .build();
    }
}
