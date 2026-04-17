package com.aadityaJi.AnchorPay.Repository;

import com.aadityaJi.AnchorPay.Entity.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<WalletEntity,Long> {
    WalletEntity findByUid(Long uid);
}
