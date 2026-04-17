package com.aadityaJi.AnchorPay.Repository;

import com.aadityaJi.AnchorPay.Entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity,Long> {
    List<TransactionEntity> findBySenderId(Long senderId);
    List<TransactionEntity> findByReceiverId(Long receiverId);
    List<TransactionEntity> findBySenderIdOrReceiverId(Long senderId,Long receiverId);
    List<TransactionEntity> findBySenderIdAndStatus(Long senderId, TransactionEntity.Status status);
}
