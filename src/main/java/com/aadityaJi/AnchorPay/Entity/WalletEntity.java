package com.aadityaJi.AnchorPay.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "wallets")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WalletEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long walletId;

    @Column(scale = 4,precision = 19)
    private BigDecimal balance;

    private long uid;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private boolean isActive;

}
