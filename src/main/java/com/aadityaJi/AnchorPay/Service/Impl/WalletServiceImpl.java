package com.aadityaJi.AnchorPay.Service.Impl;

import com.aadityaJi.AnchorPay.DTOs.WalletRequestDTO;
import com.aadityaJi.AnchorPay.Entity.UserEntity;
import com.aadityaJi.AnchorPay.Entity.WalletEntity;
import com.aadityaJi.AnchorPay.Repository.UserRepository;
import com.aadityaJi.AnchorPay.Repository.WalletRepository;
import com.aadityaJi.AnchorPay.Response.ApiResponse;
import com.aadityaJi.AnchorPay.Service.WalletService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final UserRepository userRepository;

    public WalletServiceImpl(WalletRepository walletRepository, UserRepository userRepository) {
        this.walletRepository = walletRepository;
        this.userRepository = userRepository;
    }

    @Override
    public WalletEntity addWallet(WalletRequestDTO wallet) {
        WalletEntity entity = new WalletEntity();
        entity.setUid(wallet.getUid());
        entity.setBalance(BigDecimal.ZERO);
        entity.setActive(true);

        WalletEntity savedWallet = walletRepository.save(entity);

        return savedWallet;
    }

    @Override
    public ApiResponse<BigDecimal> getBalance(String email) {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Invalid Request"));
          ApiResponse<BigDecimal> apiResponse = new ApiResponse<>();
          apiResponse.setData(user.getWallet().getBalance());
          apiResponse.setSuccess(true);
          apiResponse.setMessage("Balance Viewed at:"+System.currentTimeMillis());
          return apiResponse;
    }

    @Override
    public ApiResponse<?> addMoney(BigDecimal amount) {

            UserEntity user = findUserByFromSCH();
            WalletEntity wallet = user.getWallet();

            wallet.setBalance(wallet.getBalance().add(amount));

            user.setWallet(wallet);

            walletRepository.save(wallet);
            return ApiResponse.builder()
                    .data(wallet.getBalance())
                    .success(true)
                    .message("Money Added Successfully in Wallet:"+wallet.getWalletId())
                    .build();
    }



    public UserEntity findUserByFromSCH(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
//        System.out.println("Authenticated email = " + email);
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Invalid Request"));

    }


}
