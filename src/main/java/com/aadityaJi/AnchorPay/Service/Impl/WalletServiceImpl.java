package com.aadityaJi.AnchorPay.Service.Impl;

import com.aadityaJi.AnchorPay.DTOs.WalletRequestDTO;
import com.aadityaJi.AnchorPay.Entity.WalletEntity;
import com.aadityaJi.AnchorPay.Repository.WalletRepository;
import com.aadityaJi.AnchorPay.Service.WalletService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
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

}
